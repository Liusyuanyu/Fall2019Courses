
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