
clear;
image1 = imread('../data/Notre Dame/921919841_a30df938f2_o.jpg');
gray_image = rgb2gray(image1);
gray_image = im2double(gray_image);

[x, y, confidence, scale, orientation] = get_interest_points(image1,1);
figure('name','Result');
imshow(gray_image),title('my-Harris'),
hold on
plot(x,y, 'b*'),
hold off

% interest_point = [x(1),y(1)];

[Gmag,Gdir] = imgradient(gray_image,'prewitt');

%%
% feature_width =4;
interest_point = [x(100),y(100)];
% interest_point = [10,10];

% start_x = interest_point(1)-15;
% end_x = interest_point(1)+16;
% 
% start_y = interest_point(2)-15;
% end_y = interest_point(2)+16;

start_x = interest_point(1)-1;
end_x = interest_point(1)+2;

start_y = interest_point(2)-1;
end_y = interest_point(2)+2;

% imshow(gray_image(start_x:end_x,start_y:end_y));

subdir_im = Gdir(start_x:end_x,start_y:end_y);
submag_im = Gmag(start_x:end_x,start_y:end_y);

% subdir_im = subdir_im(submag_im~=0);
%%
part = 22.5;
edges_p=[0,part,part+45,part+45*2,part+45*3,180];
part = -180+22.5;
% edges_n=[0,part,part-45,part-45*2,part-45*3,-180];
edges_n=[-180,part,part+45,part+45*2,part+45*3,0];

gradient_hist = zeros(1,8);

subdir_im = subdir_im(submag_im~=0);
[N,~] = histcounts(subdir_im(subdir_im>0),edges_p);
gradient_hist(1:5) = N;
[N,~] = histcounts(subdir_im(subdir_im<=0),edges_n);
gradient_hist(5:8) = gradient_hist(5:8) +N(1:end-1);
gradient_hist(1) =gradient_hist(1)+ N(end);

%%

feature_width = 16;
interest_point = [5,5];

cell_width = feature_width/4;

neg_offset= floor((cell_width-1)/2);
pos_offset= ceil((cell_width-1)/2);

first_x = interest_point(1) - cell_width - neg_offset;
first_y = interest_point(2) - cell_width - neg_offset;

gradient_hist = zeros(1,8);

for x_ind = 1:4
    
    start_x = first_x + cell_width*(x_ind-1);
    end_x = start_x + cell_width-1;

    start_y = first_y + cell_width*(x_ind-1);
    end_y = start_y+ cell_width-1;
    
    disp("Coordinate");
    disp([start_x,start_y]);
    disp([end_x,end_y]);

    
end

%%
interest_point = [x(100),y(100)];
feature_width = 16;
cell_width = feature_width/4;
neg_offset= floor((cell_width-1)/2);
pos_offset= ceil((cell_width-1)/2);
[max_height,max_width] = size(Gdir);



first_x = interest_point(1) - cell_width - neg_offset;
first_y = interest_point(2) - cell_width - neg_offset;    

alldime = [];
for y_ind = 1:4
    for x_ind = 1:4
        gradient_hist = zeros(1,8);
        start_x = first_x + cell_width*(x_ind-1);
        end_x = start_x + cell_width-1;

        start_y = first_y + cell_width*(y_ind-1);
        end_y = start_y+ cell_width-1;
        
        if (start_x <=0 && end_x <=0) || (start_x >max_width && end_x >max_width)
            alldime = horzcat(alldime,gradient_hist);
            continue;
        end
        
        if (start_y <=0 && end_y <=0) || (start_y >max_height && end_y >max_height)
            alldime = horzcat(alldime,gradient_hist);
            continue;
        end
        
        if start_x <= 0
            start_x = 1;
        end  
        if end_x > max_width
            end_x = max_width;
        end
        if start_y <= 0
            start_y = 1;
        end
        if end_y > max_height
            end_y = max_height;
        end
        
        for w_x_ind = start_x:end_x
            for w_y_ind = start_y:end_y
                if(Gmag(w_y_ind,w_x_ind)==0)
                    continue;
                end
                hist_ind = directionIndex_old(Gdir(w_y_ind,w_x_ind));
                gradient_hist(hist_ind) =gradient_hist(hist_ind)+ Gmag(w_y_ind,w_x_ind);
            end
        end
        alldime = horzcat(alldime,gradient_hist);
    end
end

alldime = normalize(alldime,'norm',1);

%%

clear;
image1 = imread('../data/Notre Dame/921919841_a30df938f2_o.jpg');
gray_image = rgb2gray(image1);
gray_image = im2double(gray_image);

[x, y, confidence, scale, orientation] = get_interest_points(image1,1);

features = get_features(gray_image,x,y,16);


%%
% x = [x:1];
% 
% features = get_features(gray_image,1535,2047,8);

features2 = get_features(gray_image,x,y,16);



%% Test code place
% [Gmag2,Gdir2] = imgradient([0,0,0; 0,1,0;0,0,0],'prewitt');
% bin_edge


% rad2deg(R);

% deg2rad(180)/pi
% deg2rad(-180)/pi


% image1 = imread('../data/Notre Dame/921919841_a30df938f2_o.jpg');
% gray_image = rgb2gray(image1);
% gray_image = im2double(gray_image);
% 
% [Gmag,Gdir] = imgradient(gray_image,'prewitt');


% hist(Gdir);

% [N,edges] = histcounts(Gdir,8);

% find(Gdir>180)
% subplot(1,2,1);
% imshow(gray_image);
% subplot(1,2,2);
% plot(visualization);
% rgb2gray(image1);