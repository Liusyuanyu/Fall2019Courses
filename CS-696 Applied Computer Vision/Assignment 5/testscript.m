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


img = rgb2gray(read(flowerImageSet(1), arrTrainID(1)));
%%

imshow(img);
 
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
% featureSize =128; %SIFT-Like
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

%         %PLACEHOLDER#START
%         
%         hog = extractHOGFeatures(img,'CellSize',[32 32]);
%         features(i, :) = bagOfFeatures(img,'CustomExtractor',extractor, 'VocabularySize',128);
%         
%         
%         %PLACEHOLDER#END

    
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
fprintf('accuracy=%f\n',sum(diag(confMat))/sum(confMat(:)));
% disp(" ");
% acc = sum(diag(confMat))/sum(confMat(:));
% disp('accuracy=');
% disp(acc);

%%

% img = rgb2gray(read(flowerImageSet(1), arrTrainID(1)));
% 
% corners = detectHarrisFeatures(img);
% img = insertMarker(img,corners,'circle');
% imshow(img)

% [x1, y1] = get_interest_points(img);
% [image1_features] = get_features(img, x1, y1);

% img = rgb2gray(read(flowerImageSet(1), arrTrainID(1))); 
% hog = extractHOGFeatures(img,'CellSize',[16, 16]);
% features = MyKmeanCluster(hog,128);

% bagOfFeatures(flowerImageSet(flower),'CustomExtractor',extractor, 'VocabularySize',128);
% imshow(flowerImageSet(1));


[x1, y1] = get_interest_points(img);
[image1_features] = get_features(img, x1, y1);

%%
% centers = mySIFTBagOfVisualWord(image1_features,128);

centers = mySIFTBagOfVisualWord(image1_features,150);
features = getHistFromBOVW(image1_features,centers);








