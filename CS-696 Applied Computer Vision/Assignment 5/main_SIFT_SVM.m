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
trainingFeatures = [];
trainingLabels   = [];
targetSize = [500 450];


% words_num = 1000;
words_num = 500;
% words_num = 300;

%% For loop
codewords=[];
all_des = [];
des_num = [];

for flower = 1:numel(flowerImageSet)
    numImages = numel(arrTrainID);           
    features  = zeros(numImages, words_num);
    
    for ind = 1:numImages
        img = rgb2gray(read(flowerImageSet(flower), arrTrainID(ind)));

        % Crop image from centeriod#START
        [img_y, img_x] = size(img);
        img_y = img_y/2;
        img_x = img_x/2;
        xmin = img_x-targetSize(1)/2 -1;
        ymin = img_y-targetSize(2)/2-1;
        img = imcrop(img,[xmin, ymin, targetSize(1),targetSize(2)]);
        % Crop image from centeriod#END
        
        %PLACEHOLDER#START
        [x1, y1] = get_interest_points(img);
        afeat = get_features(img, x1, y1);
        des_num = [des_num; size(all_des,1)+1, size(afeat,1)];        
        all_des = [all_des;afeat];
        %PLACEHOLDER#END
    end
    
    codewords = mySIFTBagOfVisualWord(all_des,words_num);
    
    for ind = 1:numImages
        f_range = des_num(ind,:);
        histWords = getHistFromBOVW(all_des(f_range(1):f_range(1)+f_range(2)-1,:),codewords);
        features(ind, :) = histWords;
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
    features  = zeros(numImages, words_num);

    for ind = 1:numImages
        img = rgb2gray(read(flowerImageSet(flower), arrTestID(ind)));
        
            % Crop image from centeriod#START
        [img_y, img_x] = size(img);
        img_y = img_y/2;
        img_x = img_x/2;
        xmin = img_x-targetSize(1)/2 -1;
        ymin = img_y-targetSize(2)/2-1;
        img = imcrop(img,[xmin, ymin, targetSize(1),targetSize(2)]);
        % Crop image from centeriod#END
        
        
        %PLACEHOLDER#START
        [x1, y1] = get_interest_points(img);
        afeat = get_features(img, x1, y1);
        histWords = getHistFromBOVW(afeat,codewords);
        features(ind, :) = histWords;
        %PLACEHOLDER#END
    end
    
    % Use the imageSet Description as the training labels.  
    labels = repmat(flowerImageSet(flower).Description, numImages, 1);        
    testFeatures = [testFeatures; features];  
    testLabels   = [testLabels;   labels  ];  
end


%%
% Step 4.2: Make class predictions using the test features.
predictedLabels = predict(classifier, testFeatures);

% Step 4.3: Tabulate the results using a confusion matrix.
confMat = confusionmat(testLabels, predictedLabels);

% Step 4.4: calculate accuracy
fprintf('Word size=%d\n',words_num);
fprintf('SIFV accuracy=\n    %f\n',sum(diag(confMat))/sum(confMat(:)));

