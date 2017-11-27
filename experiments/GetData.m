function y = GetData(filename)

fid = fopen(filename);
data = textscan(fid,'%f');
data = data{:};
fid = fclose(fid);
% retrieve column 1
data(:,1);
% Number of rows 
size(data,1);

y = data;
end