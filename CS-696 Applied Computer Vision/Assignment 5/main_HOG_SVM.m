% Step-1: Load training and test data using imageSet.
imageDir   = fullfile('./data');
% imageSet recursively scans the directory tree containing the images.
flowerImageSet = imageSet(imageDir,   'recursive');

% The flowerImageSet is an array of 3 imageSet objects: one for each flower.
%flowerImageSet(1): for the type 1
%flowerImageSet(2): for the type 2
%flowerImageSet(3): for the type 3

tmparr=randperm(80);
arrTrainID=tmparr(1:50); %training ID; 
arrTestID=tmparr(51:end);% testing ID;  

%% Step-3: Train the classifier using features extracted from the training set.
%    
% Image classification is a multiclass classification problem, where you
% have to classify a flower image into one out of the three possible flower classes.
% 
% In this example, the |fitcecoc| function from the Statistics and Machine
% Learning Toolbox(TM) is used to create a multiclass classifier using
% binary SVMs.
% 
% Start by features from the training set. These features
% will be used to train the classifier.
% 
% Step 3.1 Extract features from training images
% 
% Loop over the trainingSet and extract features from each image. A
% similar procedure will be used to extract features from the testSet.

trainingFeatures = [];
trainingLabels   = [];
featureSize =5000;  % HOG
targetSize = [500 450];
hog_kernel = [16, 16];

%% Calculate size of HOG
img = rgb2gray(read(flowerImageSet(1), arrTrainID(1)));
% Crop image from centeriod
[img_y, img_x] = size(img);
img_y = img_y/2;
img_x = img_x/2;
xmin = img_x-targetSize(1)/2 -1;
ymin = img_y-targetSize(2)/2-1;
img = imcrop(img,[xmin, ymin, targetSize(1),targetSize(2)]);
hog = extractHOGFeatures(img,'CellSize',hog_kernel);

size_of_HOG = size(hog,2);


%% For loop

for flower = 1:numel(flowerImageSet)
           
    numImages = numel(arrTrainID);           
    features  = zeros(numImages, size_of_HOG);
    for i = 1:numImages
        
        img = rgb2gray(read(flowerImageSet(flower), arrTrainID(i)));

        %%%% HOG
        %PLACEHOLDER#START
        % Crop image from centeriod
        [img_y, img_x] = size(img);
        img_y = img_y/2;
        img_x = img_x/2;
        xmin = img_x-targetSize(1)/2 -1;
        ymin = img_y-targetSize(2)/2-1;
        img = imcrop(img,[xmin, ymin, targetSize(1),targetSize(2)]);
        hog = extractHOGFeatures(img,'CellSize',hog_kernel);
        features(i, :) = hog;
        %PLACEHOLDER#END
    
    end

    % Use the imageSet Description as the training labels.  
    labels = repmat(flowerImageSet(flower).Description, numImages, 1);
        
    trainingFeatures = [trainingFeatures; features];  
    trainingLabels   = [trainingLabels;   labels  ];  
        
end

%% 
classifier = fitcecoc(trainingFeatures, trainingLabels);

%%
testFeatures=[];
testLabels=[];
% Step 4.1: Loop over the testing images and extract features from each image. 
for flower = 1:numel(flowerImageSet)
     %testing images 
    numImages = numel(arrTestID); %     
    features  = zeros(numImages, size_of_HOG);
    for i = 1:numImages        
        img = rgb2gray(read(flowerImageSet(flower), arrTestID(i)));  
        
        %PLACEHOLDER#START
        % Crop image from centeriod
        [img_y, img_x] = size(img);
        img_y = img_y/2;
        img_x = img_x/2;
        xmin = img_x-targetSize(1)/2 -1;
        ymin = img_y-targetSize(2)/2-1;
        img = imcrop(img,[xmin, ymin, targetSize(1),targetSize(2)]);
        hog = extractHOGFeatures(img,'CellSize',hog_kernel);
        features(i, :) = hog;
        %PLACEHOLDER#END      
    end
    
    % Use the imageSet Description as the training labels.  
    labels = repmat(flowerImageSet(flower).Description, numImages, 1);        
    testFeatures = [testFeatures; features];  
    testLabels   = [testLabels;   labels  ];  
end

% Step 4.2: Make class predictions using the test features.
predictedLabels = predict(classifier, testFeatures);

% Step 4.3: Tabulate the results using a confusion matrix.
confMat = confusionmat(testLabels, predictedLabels);

% Step 4.4: calculate accuracy
fprintf('Feature size=%d,  Kernel:[%d,%d]\n',featureSize,hog_kernel(1),hog_kernel(2));
fprintf('HOG accuracy=\n%f\n',sum(diag(confMat))/sum(confMat(:)));

%% Find failure images
testLabels_num =zeros(size(testLabels,1),1);
predictedLabels_num =zeros(size(testLabels,1),1);
for idx = 1:size(testLabels,1)
    if strcmp(testLabels(idx,:),'Flower1')
        testLabels_num(idx) =1;
    elseif strcmp(testLabels(idx,:),'Flower2')
        testLabels_num(idx) =2;
    elseif strcmp(testLabels(idx,:),'Flower3')
        testLabels_num(idx) =3;
    end
    
    if strcmp(predictedLabels(idx,:),'Flower1')
        predictedLabels_num(idx) =1;
    elseif strcmp(predictedLabels(idx,:),'Flower2')
        predictedLabels_num(idx) =2;
    elseif strcmp(predictedLabels(idx,:),'Flower3')
        predictedLabels_num(idx) =3;
    end
end

wrong_idx = find(testLabels_num ~=predictedLabels_num );
randIdx=randperm(size(wrong_idx,1));

disp("=============Failure Images=============");
for idx = 1 : 3
    
    flower = floor( (wrong_idx(randIdx(idx)) -1) / 30) +1;
    numImages = wrong_idx(randIdx(idx))-30*(flower-1);
    img = rgb2gray(read(flowerImageSet(flower), numImages));
    figure(idx);
    imshow(img);

    fprintf('GT=%d  Predict=%d\n',testLabels_num(wrong_idx(randIdx(idx))), predictedLabels_num(wrong_idx(randIdx(idx))));
    
%     fprintf('Ground Truth=%s\n',append("Flower",num2str(testLabels_num(wrong_idx(randIdx(idx))))));
%     fprintf('Predict=%s\n',append("Flower",num2str(predictedLabels_num(wrong_idx(randIdx(idx))))));
end