function [x, y] = get_interest_points(image)
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
    
    Q=0.005;
%     Q=0.01;
    R_corner=(harris>=(Q*RMax)).*harris;
    R_localMax = colfilt(R_corner,[3 3],'sliding',@max);
    
    match = (R_localMax == R_corner).*R_corner;
    [y, x] = find(match(2:rows-1,2:columns-1)); 
    y = y+1;
    x = x+1;
end
