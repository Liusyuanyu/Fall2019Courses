% Step-1: Load training and test data using imageSet.
imageDir   = fullfile('./data');
% imageSet recursively scans the directory tree containing the images.
flowerImageSet = imageSet(imageDir,   'recursive');

tmparr=randperm(80);
arrTrainID=tmparr(1:50); %training ID; 
arrTestID=tmparr(51:end);% testing ID;  
 
%% Step-3: Train the classifier using features extracted from the training set.
trainingFeatures = [];
trainingLabels   = [];
binOfHist = 30;
% binOfHist = 50;
targetSize = [500 450];

%% For loop

for flower = 1:numel(flowerImageSet)
    numImages = numel(arrTrainID);           
    features  = zeros(numImages, binOfHist*3);
    
    for ind = 1:numImages
        img = (read(flowerImageSet(flower), arrTrainID(ind)));
        
        % Crop image from centeriod#START
        [img_y, img_x,~] = size(img);
        img_y = img_y/2;
        img_x = img_x/2;
        xmin = img_x-targetSize(1)/2 -1;
        ymin = img_y-targetSize(2)/2-1;
        img = imcrop(img,[xmin, ymin, targetSize(1),targetSize(2)]);
        % Crop image from centeriod#END
        
        
        %PLACEHOLDER#START        
        [yRed, ~] = imhist(img(:,:,1),binOfHist); 
        [yGreen, ~] = imhist(img(:,:,2),binOfHist);
        [yBlue, ~] = imhist(img(:,:,3),binOfHist);
        totalPixel = sum(yRed);
        yRed = yRed / (totalPixel);
        yGreen = yGreen / (totalPixel);
        yBlue = yBlue / (totalPixel);
        
        features(ind, 1:binOfHist) = yRed(:);
        features(ind, binOfHist+1:binOfHist*2) = yGreen(:);
        features(ind, binOfHist*2+1:binOfHist*3) = yBlue(:);
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
    features  = zeros(numImages, binOfHist*3);

    for ind = 1:numImages
        img = read(flowerImageSet(flower), arrTestID(ind));

        % Crop image from centeriod#START
        [img_y, img_x,~] = size(img);
        img_y = img_y/2;
        img_x = img_x/2;
        xmin = img_x-targetSize(1)/2 -1;
        ymin = img_y-targetSize(2)/2-1;
        img = imcrop(img,[xmin, ymin, targetSize(1),targetSize(2)]);
        % Crop image from centeriod#END
        
        %PLACEHOLDER#START
        [yRed, ~] = imhist(img(:,:,1),binOfHist); 
        [yGreen, ~] = imhist(img(:,:,2),binOfHist);
        [yBlue, ~] = imhist(img(:,:,3),binOfHist);
        totalPixel = sum(yRed);
        yRed = yRed / (totalPixel);
        yGreen = yGreen / (totalPixel);
        yBlue = yBlue / (totalPixel);
        
        features(ind, 1:binOfHist) = yRed(:);
        features(ind, binOfHist+1:binOfHist*2) = yGreen(:);
        features(ind, binOfHist*2+1:binOfHist*3) = yBlue(:);
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
fprintf('Bin size=%d\n',binOfHist);
fprintf('HOC accuracy=\n%f\n',sum(diag(confMat))/sum(confMat(:)));

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
    
    flower = floor((wrong_idx(randIdx(idx))-1) / 30) +1;
    numImages = wrong_idx(randIdx(idx))-30*(flower-1);
    img = (read(flowerImageSet(flower), numImages));
    
    figure(idx);
    imshow(img);

    fprintf('%d : GT=%d  Predict=%d\n',idx,testLabels_num(wrong_idx(randIdx(idx))), predictedLabels_num(wrong_idx(randIdx(idx))));
    
%     fprintf('Ground Truth=%s\n',append("Flower",num2str(testLabels_num(wrong_idx(randIdx(idx))))));
%     fprintf('Predict=%s\n',append("Flower",num2str(predictedLabels_num(wrong_idx(randIdx(idx))))));
end     
%%