%% Defining the filenames for each algo

uniformCostSearch = "Uniform Cost.txt";

A_Star_H1 = "A*H1.txt";
A_Star_H2 = "A*H2.txt";
A_Star_H3 = "A*H3.txt";
A_Star_H4 = "A*H4.txt";
A_Star_H5 = "A*H5.txt";

%W1 = 1.25 W2 = 2
A_Star_Weighted_H1 = "Weighted A*H1.txt";
A_Star_Weighted_H2 = "Weighted A*H2.txt";
A_Star_Weighted_H3 = "Weighted A*H3.txt";
A_Star_Weighted_H4 = "Weighted A*H4.txt";
A_Star_Weighted_H5 = "Weighted A*H5.txt";

%W1 = 1.25 W2 = 2
A_Star_Sequential = "Sequential A*.txt";

%%Extracting the data from the text files
ucs_data = GetData(uniformCostSearch);

a_star_h1_data = GetData(A_Star_H1);
a_star_h2_data = GetData(A_Star_H2);
a_star_h3_data = GetData(A_Star_H3);
a_star_h4_data = GetData(A_Star_H4);
a_star_h5_data = GetData(A_Star_H5);

a_star_weighted_h1_data = GetData(A_Star_Weighted_H1);
a_star_weighted_h2_data = GetData(A_Star_Weighted_H2);
a_star_weighted_h3_data = GetData(A_Star_Weighted_H3);
a_star_weighted_h4_data = GetData(A_Star_Weighted_H4);
a_star_weighted_h5_data = GetData(A_Star_Weighted_H5);

a_star_sequential_data  = GetData(A_Star_Sequential);

% Clearing the file name data
clear uniformCostSearch,
clear A_Star_H1, clear A_Star_H2, clear A_Star_H3, clear A_Star_H4,
clear A_Star_H5, clear A_Star_Weighted_H1, clear A_Star_Weighted_H2,
clear A_Star_Weighted_H3, clear A_Star_Weighted_H4, clear A_Star_Weighted_H5
clear A_Star_Sequential;
%% Getting data into separate matrices

DATASIZE = length(ucs_data);
% DEFINING DATA STEPS
nodes_explored = 1:4:DATASIZE;
path_length    = 2:4:DATASIZE;
path_cost      = 3:4:DATASIZE;
time_ms        = 4:4:DATASIZE;


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
ucs_nodes    = ucs_data(nodes_explored);
ucs_length   = ucs_data(path_length);
ucs_cost     = ucs_data(path_cost);
ucs_time     = ucs_data(time_ms);

%Clearing ucs_data
clear ucs_data

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%a_star_data
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
a_star_h1_nodes   = a_star_h1_data(nodes_explored);
a_star_h1_length  = a_star_h1_data(path_length);
a_star_h1_cost    = a_star_h1_data(path_cost);
a_star_h1_time    = a_star_h1_data(time_ms);

a_star_h2_nodes   = a_star_h2_data(nodes_explored);
a_star_h2_length  = a_star_h2_data(path_length);
a_star_h2_cost    = a_star_h2_data(path_cost);
a_star_h2_time    = a_star_h2_data(time_ms);

a_star_h3_nodes   = a_star_h3_data(nodes_explored);
a_star_h3_length  = a_star_h3_data(path_length);
a_star_h3_cost    = a_star_h3_data(path_cost);
a_star_h3_time    = a_star_h3_data(time_ms);

a_star_h4_nodes   = a_star_h4_data(nodes_explored);
a_star_h4_length  = a_star_h4_data(path_length);
a_star_h4_cost    = a_star_h4_data(path_cost);
a_star_h4_time    = a_star_h4_data(time_ms);

a_star_h5_nodes   = a_star_h5_data(nodes_explored);
a_star_h5_length  = a_star_h5_data(path_length);
a_star_h5_cost    = a_star_h5_data(path_cost);
a_star_h5_time    = a_star_h5_data(time_ms);
          
%Clearing a_star_data
clear a_star_h1_data, clear a_star_h2_data, clear a_star_h3_data
clear a_star_h4_data, clear a_star_h5_data;

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% a_star_weighted data 
a_star_weighted_h1_nodes   = a_star_weighted_h1_data(nodes_explored);
a_star_weighted_h1_length  = a_star_weighted_h1_data(path_length);
a_star_weighted_h1_cost    = a_star_weighted_h1_data(path_cost);
a_star_weighted_h1_time    = a_star_weighted_h1_data(time_ms);

a_star_weighted_h2_nodes   = a_star_weighted_h2_data(nodes_explored);
a_star_weighted_h2_length  = a_star_weighted_h2_data(path_length);
a_star_weighted_h2_cost    = a_star_weighted_h2_data(path_cost);
a_star_weighted_h2_time    = a_star_weighted_h2_data(time_ms);

a_star_weighted_h3_nodes   = a_star_weighted_h3_data(nodes_explored);
a_star_weighted_h3_length  = a_star_weighted_h3_data(path_length);
a_star_weighted_h3_cost    = a_star_weighted_h3_data(path_cost);
a_star_weighted_h3_time    = a_star_weighted_h3_data(time_ms);

a_star_weighted_h4_nodes   = a_star_weighted_h4_data(nodes_explored);
a_star_weighted_h4_length  = a_star_weighted_h4_data(path_length);
a_star_weighted_h4_cost    = a_star_weighted_h4_data(path_cost);
a_star_weighted_h4_time    = a_star_weighted_h4_data(time_ms);

a_star_weighted_h5_nodes   = a_star_weighted_h5_data(nodes_explored);
a_star_weighted_h5_length  = a_star_weighted_h5_data(path_length);
a_star_weighted_h5_cost    = a_star_weighted_h5_data(path_cost);
a_star_weighted_h5_time    = a_star_weighted_h5_data(time_ms);

%Clearing a_star_weighted_data
clear a_star_weighted_h1_data, clear a_star_weighted_h2_data,
clear a_star_weighted_h3_data, clear a_star_weighted_h4_data
clear a_star_weighted_h5_data

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% a_star_sequential data 
a_star_sequential_nodes   = a_star_sequential_data(nodes_explored);
a_star_sequential_length  = a_star_sequential_data(path_length);
a_star_sequential_cost    = a_star_sequential_data(path_cost);
a_star_sequential_time    = a_star_sequential_data(time_ms);

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
clear a_star_sequential_data
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%Clearing uneeded data
clear DATASIZE, clear nodes_explored, clear path_length,
clear path_cost, clear time_ms;


%% Mean Time

ucs_mean_time  = mean(ucs_time);

a_star_h1_mean_time = mean(a_star_h1_time);
a_star_h2_mean_time = mean(a_star_h2_time);
a_star_h3_mean_time = mean(a_star_h3_time);
a_star_h4_mean_time = mean(a_star_h4_time);
a_star_h5_mean_time = mean(a_star_h5_time);

a_star_weighted_h1_mean_time = mean(a_star_weighted_h1_time);
a_star_weighted_h2_mean_time = mean(a_star_weighted_h2_time);
a_star_weighted_h3_mean_time = mean(a_star_weighted_h3_time);
a_star_weighted_h4_mean_time = mean(a_star_weighted_h4_time);
a_star_weighted_h5_mean_time = mean(a_star_weighted_h5_time);

a_star_sequential_mean_time = mean(a_star_sequential_time);


fprintf("ucs_mean_time:  %.4f ms\n", ucs_mean_time);
fprintf("---------------------------------\n");
fprintf("a_star_h1_mean_time: %1.4f ms\n", a_star_h1_mean_time);
fprintf("a_star_h2_mean_time: %1.4f ms\n", a_star_h2_mean_time);
fprintf("a_star_h3_mean_time: %1.4f ms\n", a_star_h3_mean_time);
fprintf("a_star_h4_mean_time: %1.4f ms\n", a_star_h4_mean_time);
fprintf("a_star_h5_mean_time: %1.4f ms\n", a_star_h5_mean_time);
fprintf("---------------------------------\n");
fprintf("a_star_weighted_h1_mean_time: %.4f ms\n", a_star_weighted_h1_mean_time);
fprintf("a_star_weighted_h2_mean_time: %.4f ms\n", a_star_weighted_h2_mean_time);
fprintf("a_star_weighted_h3_mean_time: %.4f ms\n", a_star_weighted_h3_mean_time);
fprintf("a_star_weighted_h4_mean_time: %.4f ms\n", a_star_weighted_h4_mean_time);
fprintf("a_star_weighted_h5_mean_time: %.4f ms\n", a_star_weighted_h5_mean_time);
fprintf("---------------------------------\n");
fprintf("a_star_sequential_mean_time: %.4f ms\n\n", a_star_sequential_mean_time);

%%
%Clearing uneeded data
clear ucs_time, clear a_star_h1_time, clear a_star_h2_time, 
clear a_star_h3_time, clear a_star_h4_time, clear a_star_h5_time,
clear a_star_weighted_h1_time, clear a_star_weighted_h2_time, 
clear a_star_weighted_h3_time , clear a_star_weighted_h4_time,
clear a_star_weighted_h5, clear a_star_sequential_time;

%% Mean Nodes Explored
ucs_mean_nodes  = mean(ucs_nodes);

a_star_h1_mean_nodes = mean(a_star_h1_nodes);
a_star_h2_mean_nodes = mean(a_star_h2_nodes);
a_star_h3_mean_nodes = mean(a_star_h3_nodes);
a_star_h4_mean_nodes = mean(a_star_h4_nodes);
a_star_h5_mean_nodes = mean(a_star_h5_nodes);

a_star_weighted_h1_mean_nodes = mean(a_star_weighted_h1_nodes);
a_star_weighted_h2_mean_nodes = mean(a_star_weighted_h2_nodes);
a_star_weighted_h3_mean_nodes = mean(a_star_weighted_h3_nodes);
a_star_weighted_h4_mean_nodes = mean(a_star_weighted_h4_nodes);
a_star_weighted_h5_mean_nodes = mean(a_star_weighted_h5_nodes);

a_star_sequential_mean_nodes = mean(a_star_sequential_nodes);


fprintf("ucs_mean_nodes:  %.4f  \n", ucs_mean_nodes);
fprintf("---------------------------------\n");
fprintf("a_star_h1_mean_nodes: %1.4f  \n", a_star_h1_mean_nodes);
fprintf("a_star_h2_mean_nodes: %1.4f  \n", a_star_h2_mean_nodes);
fprintf("a_star_h3_mean_nodes: %1.4f  \n", a_star_h3_mean_nodes);
fprintf("a_star_h4_mean_nodes: %1.4f  \n", a_star_h4_mean_nodes);
fprintf("a_star_h5_mean_nodes: %1.4f  \n", a_star_h5_mean_nodes);
fprintf("---------------------------------\n");
fprintf("a_star_weighted_h1_mean_nodes: %.4f  \n", a_star_weighted_h1_mean_nodes);
fprintf("a_star_weighted_h2_mean_nodes: %.4f  \n", a_star_weighted_h2_mean_nodes);
fprintf("a_star_weighted_h3_mean_nodes: %.4f  \n", a_star_weighted_h3_mean_nodes);
fprintf("a_star_weighted_h4_mean_nodes: %.4f  \n", a_star_weighted_h4_mean_nodes);
fprintf("a_star_weighted_h5_mean_nodes: %.4f  \n", a_star_weighted_h5_mean_nodes);
fprintf("---------------------------------\n");
fprintf("a_star_sequential_mean_nodes: %.4f  \n\n", a_star_sequential_mean_nodes);


%%
%Clearing uneeded data
clear ucs_nodes, clear a_star_h1_nodes, clear a_star_h2_nodes, 
clear a_star_h3_nodes, clear a_star_h4_nodes, clear a_star_h5_nodes,
clear a_star_weighted_h1_nodes, clear a_star_weighted_h2_nodes, 
clear a_star_weighted_h3_nodes , clear a_star_weighted_h4_nodes,
clear a_star_weighted_h5, clear a_star_sequential_nodes;
%% Mean Path Length

ucs_mean_length  = mean(ucs_length);

a_star_h1_mean_length = mean(a_star_h1_length);
a_star_h2_mean_length = mean(a_star_h2_length);
a_star_h3_mean_length = mean(a_star_h3_length);
a_star_h4_mean_length = mean(a_star_h4_length);
a_star_h5_mean_length = mean(a_star_h5_length);

a_star_weighted_h1_mean_length = mean(a_star_weighted_h1_length);
a_star_weighted_h2_mean_length = mean(a_star_weighted_h2_length);
a_star_weighted_h3_mean_length = mean(a_star_weighted_h3_length);
a_star_weighted_h4_mean_length = mean(a_star_weighted_h4_length);
a_star_weighted_h5_mean_length = mean(a_star_weighted_h5_length);

a_star_sequential_mean_length = mean(a_star_sequential_length);


fprintf("ucs_mean_length:  %.4f  \n", ucs_mean_length);
fprintf("---------------------------------\n");
fprintf("a_star_h1_mean_length: %1.4f  \n", a_star_h1_mean_length);
fprintf("a_star_h2_mean_length: %1.4f  \n", a_star_h2_mean_length);
fprintf("a_star_h3_mean_length: %1.4f  \n", a_star_h3_mean_length);
fprintf("a_star_h4_mean_length: %1.4f  \n", a_star_h4_mean_length);
fprintf("a_star_h5_mean_length: %1.4f  \n", a_star_h5_mean_length);
fprintf("---------------------------------\n");
fprintf("a_star_weighted_h1_mean_length: %.4f  \n", a_star_weighted_h1_mean_length);
fprintf("a_star_weighted_h2_mean_length: %.4f  \n", a_star_weighted_h2_mean_length);
fprintf("a_star_weighted_h3_mean_length: %.4f  \n", a_star_weighted_h3_mean_length);
fprintf("a_star_weighted_h4_mean_length: %.4f  \n", a_star_weighted_h4_mean_length);
fprintf("a_star_weighted_h5_mean_length: %.4f  \n", a_star_weighted_h5_mean_length);
fprintf("---------------------------------\n");
fprintf("a_star_sequential_mean_length: %.4f  \n\n", a_star_sequential_mean_length);

%%
clear ucs_length, clear a_star_h1_length, clear a_star_h2_length, 
clear a_star_h3_length, clear a_star_h4_length, clear a_star_h5_length,
clear a_star_weighted_h1_length, clear a_star_weighted_h2_length, 
clear a_star_weighted_h3_length , clear a_star_weighted_h4_length,
clear a_star_weighted_h5, clear a_star_sequential_length;
%% Mean Path Cost

ucs_mean_cost  = mean(ucs_cost);

a_star_h1_mean_cost = mean(a_star_h1_cost);
a_star_h2_mean_cost = mean(a_star_h2_cost);
a_star_h3_mean_cost = mean(a_star_h3_cost);
a_star_h4_mean_cost = mean(a_star_h4_cost);
a_star_h5_mean_cost = mean(a_star_h5_cost);

a_star_weighted_h1_mean_cost = mean(a_star_weighted_h1_cost);
a_star_weighted_h2_mean_cost = mean(a_star_weighted_h2_cost);
a_star_weighted_h3_mean_cost = mean(a_star_weighted_h3_cost);
a_star_weighted_h4_mean_cost = mean(a_star_weighted_h4_cost);
a_star_weighted_h5_mean_cost = mean(a_star_weighted_h5_cost);

a_star_sequential_mean_cost = mean(a_star_sequential_cost);


fprintf("ucs_mean_cost:  %.4f  \n", ucs_mean_cost);
fprintf("---------------------------------\n");
fprintf("a_star_h1_mean_cost: %1.4f  \n", a_star_h1_mean_cost);
fprintf("a_star_h2_mean_cost: %1.4f  \n", a_star_h2_mean_cost);
fprintf("a_star_h3_mean_cost: %1.4f  \n", a_star_h3_mean_cost);
fprintf("a_star_h4_mean_cost: %1.4f  \n", a_star_h4_mean_cost);
fprintf("a_star_h5_mean_cost: %1.4f  \n", a_star_h5_mean_cost);
fprintf("---------------------------------\n");
fprintf("a_star_weighted_h1_mean_cost: %.4f  \n", a_star_weighted_h1_mean_cost);
fprintf("a_star_weighted_h2_mean_cost: %.4f  \n", a_star_weighted_h2_mean_cost);
fprintf("a_star_weighted_h3_mean_cost: %.4f  \n", a_star_weighted_h3_mean_cost);
fprintf("a_star_weighted_h4_mean_cost: %.4f  \n", a_star_weighted_h4_mean_cost);
fprintf("a_star_weighted_h5_mean_cost: %.4f  \n", a_star_weighted_h5_mean_cost);
fprintf("---------------------------------\n");
fprintf("a_star_sequential_mean_cost: %.4f  \n\n", a_star_sequential_mean_cost);

%%
clear ucs_cost, clear a_star_h1_cost, clear a_star_h2_cost, 
clear a_star_h3_cost, clear a_star_h4_cost, clear a_star_h5_cost,
clear a_star_weighted_h1_cost, clear a_star_weighted_h2_cost, 
clear a_star_weighted_h3_cost , clear a_star_weighted_h4_cost,
clear a_star_weighted_h5, clear a_star_sequential_cost;


