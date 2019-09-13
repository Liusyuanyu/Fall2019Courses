%% Part I: 1-3

%%% a.Sort all the intensities in A, put the result in a single 10,000-dimensional vector x, and plot the values in x.
A = randi([0,255],100,100);
A = uint8(A);
x = sort(A(:));
x = reshape(x, 1, 10000);  
plot(x);

%% B.
%%%b.Display a figure showing a histogram of A’s intensities with 32 bins
histogram(A, 32)

%% C
%%%c. Create and display a new binary image the same size as A, which is white wherever 
%%%     the intensity in A is greater than a threshold t, and black everywhere else.

t = 80;
A_binary_Image = zeros(100);
A_binary_Image = uint8(A_binary_Image);
white_matrix = ones(100)*255;
white_matrix  = uint8(white_matrix);

greater_index = find(A > t);
A_binary_Image(greater_index) = white_matrix(greater_index);

imshow(A_binary_Image);

%% D
% d. Generate a new image (matrix), which is the same as A, but with A’s mean intensity
%   value subtracted from each pixel. Set any negative values to 0.
mean_of_A =  mean(x);
d_A = A - mean_of_A;

% imshow(d_A);

%% E
% e.	Use rand to write a function that returns the roll of a six-sided die.
result = roll_six_sided_die();
display(result);

%% F
% f. Let y be the vector: y = [1:6]. Use the reshape command to form a new 
%       matrix z whose first column is [1, 2, 3]’, and whose second column is [4, 5, 6]’.
y = [1:6];
z = reshape(y, [3,2]);

%% G
% g. Use the min and find functions to set x to the single minimum value that occurs in A,
%       and set r to the row it occurs in and c to the column it occurs in.
x = min(min(A));
min_index = find(A ==x);
[r_ind,c_ind]= find(A ==x);

r = A(r_ind, :);
c = A(:,c_ind);


%% H
% h. Let v be the vector: v = [1 8 8 2 1 3 9 8]. Use the unique function to compute the total 
%       number of unique values that occur in v.
v = [1 8 8 2 1 3 9 8];
number_of_unique_val= size(unique(v),2);
