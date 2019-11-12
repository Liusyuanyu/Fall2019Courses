% Local Feature Stencil Code

% Returns a set of interest points for the input image

% 'image' can be grayscale or color, your choice.
% 'feature_width', in pixels, is the local feature width. It might be
%   useful in this function in order to (a) suppress boundary interest
%   points (where a feature wouldn't fit entirely in the image, anyway)
%   or(b) scale the image filters being used. Or you can ignore it.

% 'x' and 'y' are nx1 vectors of x and y coordinates of interest points.
% 'confidence' is an nx1 vector indicating the strength of the interest
%   point. You might use this later or not.
% 'scale' and 'orientation' are nx1 vectors indicating the scale and
%   orientation of each interest point. These are OPTIONAL. By default you
%   do not need to make scale and orientation invariant local features.
function [x, y, confidence, scale, orientation] = get_interest_points(image, feature_width)

% Implement the Harris corner detector (See Szeliski 4.1.1) to start with.
% You can create additional interest point detector functions (e.g. MSER)
% for extra credit.
    if size(image,3)==3
        gray_image = rgb2gray(image);
    else
        gray_image = image;
    end
    gray_image = im2double(gray_image );

    gaussian_f = fspecial('gaussian');
    gray_image = imfilter(gray_image, gaussian_f);
    
    [Gx, Gy] = imgradientxy(gray_image,'sobel');
    Ix2 = Gx.^2;
    Iy2 = Gy.^2;
    Ixy = Gx.*Gy;
    
    Ix2 = imfilter(Ix2, gaussian_f);
    Iy2 = imfilter(Iy2, gaussian_f);
    Ixy = imfilter(Ixy, gaussian_f);
      
    [rows,columns] = size(gray_image);
%     k = 0.04;
    k = 0.06;
    
    harris = Ix2 .* Iy2 - Ixy.^2 - k .*((Ix2 + Iy2).^2);
    [RMax,~]=max(harris(:));
    RMax = RMax(1);
    
    Q=0.01;
%     Q=0.1;
    R_corner=(harris>=(Q*RMax)).*harris;
    R_localMax = colfilt(R_corner,[3 3],'sliding',@max);
    
    match = (R_localMax == R_corner).*R_corner;
    [y, x, confidence] = find(match(2:rows-1,2:columns-1)); 
    y = y+1;
    x = x+1;
    
    scale=0;
    orientation=0;
end

