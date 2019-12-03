function [features] = get_features(image, x, y)
    [feature_num,~] =size(x); 
    
    features = zeros(feature_num,128);

%     cell_width = 12/4;
    cell_width = 16/4;
    neg_offset= floor((cell_width-1)/2);
    [Gmag,Gdir] = imgradient(image,'sobel');
    [max_height,max_width] = size(Gdir);

    for f_ind = 1:feature_num
        interest_point = [x(f_ind),y(f_ind)];
        first_x = interest_point(1) - cell_width - neg_offset;
        first_y = interest_point(2) - cell_width - neg_offset;    

        alldime = zeros(1,128);
        cell_count = 0;
        for y_ind = 1:4
            start_y = first_y + cell_width*(y_ind-1);
            end_y = start_y+ cell_width-1;
            
            for x_ind = 1:4
                gradient_hist = zeros(1,8);
                start_x = first_x + cell_width*(x_ind-1);
                end_x = start_x + cell_width-1;

                if (start_x <=0 && end_x <=0) || (start_x >max_width && end_x >max_width)
                    alldime(1+8*(cell_count):8+8*(cell_count))=gradient_hist;
                    continue;
                end

                if (start_y <=0 && end_y <=0) || (start_y >max_height && end_y >max_height)
                    alldime(1+8*(cell_count):8+8*(cell_count))=gradient_hist;
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
                        hist_ind = directionIndex(Gdir(w_y_ind,w_x_ind));
                        gradient_hist(hist_ind) =gradient_hist(hist_ind)+ Gmag(w_y_ind,w_x_ind);
                    end
                end
                alldime(1+8*(cell_count):8+8*(cell_count))=gradient_hist;
                cell_count = cell_count+1;
            end
        end

        alldime = normalize(alldime,'norm',1);
%         alldime = alldime / sum(alldime);
        tf = isnan(alldime);
        if sum(tf(:)) ~=0
            alldime(tf==1) = 0;
        end
        
        features(f_ind,:) =alldime; 
    end
end

function hist_ind = directionIndex(dir) 
    hist_ind=0;
    if(dir >= -22.5 && dir <= -22.5+45)
        hist_ind = 1;
    elseif (dir > 22.5 && dir <= 22.5+45)
        hist_ind = 2;
    elseif (dir > 67.5 && dir <= 67.5+45)
        hist_ind = 3;
    elseif (dir > 112.5 && dir <= 112.5+45)
        hist_ind = 4;    
    elseif ((dir > 157.5 && dir <= 180) || (dir >= -180 && dir <= -180+22.5))
        hist_ind = 5;    
    elseif (dir > -157.5 && dir <= -157.5+45)
        hist_ind = 6;
    elseif (dir > -112.5 && dir <= -112.5+45)
        hist_ind = 7;
    elseif (dir > -67.5 && dir < -67.5+45)
        hist_ind = 8;
    else
        hist_ind = 1;
    end
end







