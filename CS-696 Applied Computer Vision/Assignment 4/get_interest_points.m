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

% You are free to experiment. Here are some helpful functions:
%  BWLABEL and the newer BWCONNCOMP will find connected components in 
% thresholded binary image. You could, for instance, take the maximum value
% within each component.
%  COLFILT can be used to run a max() operator on each sliding window. You
% could use this to ensure that every interest point is at a local maximum
% of cornerness.

    if size(image,3)==3
        gray_image = rgb2gray(image);
    else
        gray_image = image;
    end
    gray_image = im2double(gray_image );

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
    
    %?Q*RMax???????????????
    Q=0.1;
    R_corner=(r_mat>=(Q*RMax)).*r_mat;
    R_localMax = colfilt(r_mat,[3 3],'sliding',@max);
    [row,col]=find(R_localMax(2:rows-1,2:columns-1)==R_corner(2:rows-1,2:columns-1) & R_corner(2:rows-1,2:columns-1)~=0);
    row = row+1;
    col = col+1;
    


    x=col;
    y=row;
    confidence=0;
    scale=0;
    orientation=0;

end

