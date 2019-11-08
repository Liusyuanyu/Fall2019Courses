% close all;
clear;

image1 = imread('../data/Notre Dame/921919841_a30df938f2_o.jpg');
gray_image = rgb2gray(image1);
gray_image = im2double(gray_image);
% image2 = imread('../data/Notre Dame/4191453057_c86028ce1f_o.jpg');
% gray_image2 = im2double(gray_image);

% imshow(image1);
%%
get_interest_points(image1,1);

%%
[Gx, Gy] = imgradientxy(gray_image,'prewitt');
% [Gx, Gy] = imgradientxy(gray_image);

figure(1)
imshowpair(Gx, Gy, 'montage');
 title('Directional Gradients: x-direction, Gx (left), y-direction, Gy (right), using Prewitt method')
axis off;

%%
% large_1d_blur_filter = fspecial('Gaussian', [25 1], 10);
% blur_filter=fspecial('gaussian',[5 1],1.5);

[x_dir,y_dir] = meshgrid(-1:1,-1:1);


X=imfilter(gray_image,x_dir);
% X=imfilter(gray_image,[1 0 -1]);
% X=imfilter(X,blur_filter);

Y=imfilter(gray_image,y_dir);
% Y=imfilter(gray_image,[-1 0 1]');
% Y=imfilter(gray_image,[1 0 -1]');
% Y=imfilter(Y,blur_filter);

figure(2);
imshowpair(X, Y, 'montage');
 title('Directional Gradients: x-direction, Gx (left), y-direction, Gy (right), using Prewitt method')
axis off;
%%
[Gx, Gy] = imgradientxy(gray_image,'prewitt');
    
Ix2 = Gx.^2;
Iy2 = Gy.^2;
Ixy = Gx.*Gy;

gaussian_f = fspecial('gaussian');
Ix2 = imfilter(Ix2, gaussian_f);
Iy2 = imfilter(Iy2, gaussian_f);
Ixy = imfilter(Ixy, gaussian_f);

[rows,columns] = size(gray_image);
r_mat = zeros(rows,columns);
k = 0.04;
RMax = 0;

for row_ind=1:rows
    for col_ind=1:columns
        M=[Ix2(row_ind,col_ind) Ixy(row_ind,col_ind);Ixy(row_ind,col_ind) Iy2(row_ind,col_ind)];
        r_mat(row_ind,col_ind)=det(M) - k*(trace(M))^2;
        if(r_mat(row_ind,col_ind)>RMax)
            RMax=r_mat(row_ind,col_ind);
        end
    end
end

%%


% find(r_mat > 0.01)

%?Q*RMax???????????????
% Q=0.01;
Q=0.1;
% R_corner=(r_mat>=(Q)).*r_mat;
R_corner=(r_mat>=(Q*RMax)).*r_mat;

%??3x3???????????????8?????????????????????
R_localMax = colfilt(r_mat,[3 3],'sliding',@max);

%?????????????8??????????????
%??????????
[row,col]=find(R_localMax(2:rows-1,2:columns-1)==R_corner(2:rows-1,2:columns-1) & R_corner(2:rows-1,2:columns-1)~=0);

figure('name','Result');
% subplot(1,2,1);
subplot(1,3,1);
imshow(gray_image),title('With-NMS'),
hold on
plot(col,row, 'b*'),
hold off

[row_woNWS,col_woNWS]=find( r_mat>=(Q*RMax) );
subplot(1,3,2);
% subplot(1,2,2);
imshow(gray_image),title('Without-NMS'),
hold on
plot(col_woNWS,row_woNWS, 'r+'),
hold off



% [row_woNWS,col_woNWS]=find( r_mat>=(Q*RMax) );
% subplot(1,2,2),imshow(gray_image),title('Without-NMS'),
% hold on
% plot(col_woNWS,row_woNWS, 'r+'),
% hold off

row_diff = ismember(row_woNWS, row, 'rows');
subplot(1,3,3),imshow(gray_image),title('Different'),
hold on
plot(col_woNWS(row_diff),row_woNWS(row_diff), 'y+'),
hold off
row_woNWS(row_diff)


% C = corner(gray_image);
% subplot(1,2,2),imshow(gray_image),title('matlab-conner'),
% hold on
% plot(C(:,1), C(:,2), 'r*');
% hold off

%%

% avg_r = mean(r_mat(:));
% % threshold = abs(100* avg_r);
% threshold = abs(5 * avg_r);
% % threshold = abs(avg_r);
% % threshold = (avg_r);

bwIm = zeros(size(r_mat));
bwIm(r_mat > threshold) = 1;
bwresult = bwconncomp(bwIm,4);

[rows, cols] = size(r_mat);

row = zeros(bwresult.NumObjects,1);
col = zeros(bwresult.NumObjects,1);

for ind = 1:bwresult.NumObjects
    pixelMat = cell2mat(bwresult.PixelIdxList(ind));
    [row(ind),col(ind)]=ind2sub([rows, cols],pixelMat(end));
end

figure('name','Result');
subplot(1,2,1);
imshow(gray_image),title('my-Harris'),
hold on
plot(col,row, 'b*'),
hold off


C = corner(gray_image);
subplot(1,2,2),imshow(gray_image),title('matlab-conner'),
hold on
plot(C(:,1), C(:,2), 'r*');
hold off

%%

% %?Q*RMax???????????????
% Q=0.01;
% R_corner=(r_mat>=(Q*RMax)).*r_mat;
% 
% %??3x3???????????????8?????????????????????
% fun = @(x) max(x(:)); 
% R_localMax = nlfilter(r_mat,[3 3],fun); 
% 
% % ?????????????8??????????????
% % ??????????
% [row,col]=find(R_localMax(2:rows-1,2:columns-1)==R_corner(2:rows-1,2:columns-1));

%%
% set threshold of 'cornerness' to 5 times average R score
avg_r = mean(r_mat(:));
threshold = abs(100* avg_r);
% threshold = abs(5 * avg_r);

[row, col] = find(r_mat > threshold);
% scores = [];
% for index = 1:size(row,1)
%     %see what the values are
%     r = row(index);
%     c = col(index);
%     scores = cat(2, scores,H(r,c));
% 
% end


figure('name','Result');
subplot(1,2,1);
imshow(gray_image),title('my-Harris'),
hold on
plot(col,row, 'b*'),
hold off

C = corner(gray_image);
subplot(1,2,2),imshow(gray_image),title('matlab-conner'),
hold on
plot(C(:,1), C(:,2), 'r*');
hold off

%%
imshow(gray_image);