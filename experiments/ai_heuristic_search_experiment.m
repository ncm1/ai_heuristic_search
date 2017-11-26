%%
%Extra Credit AI - getting 

% opening training/validation data + sets
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


ucs_data = GetData(uniformCostSearch);

a_star_h1_data = GetData(A_Star_H1);
a_star_h2_data = GetData(A_Star_H2);
a_star_h3_data = GetData(A_Star_H3);
a_star_h4_data = GetData(A_Star_H4);
a_star_h5_data = GetData(A_Star_H5);

%%
a_star_weighted_h1_data = GetData(A_Star_Weighted_H1);
a_star_weighted_h2_data = GetData(A_Star_Weighted_H2);
a_star_weighted_h3_data = GetData(A_Star_Weighted_H3);
a_star_weighted_h4_data = GetData(A_Star_Weighted_H4);
a_star_weighted_h5_data = GetData(A_Star_Weighted_H5);
%%



trainingData = SplitData(trainingSetPath);
trainingLabels = GetLabels(trainingLabelsPath);

cell = trainingData(1);
puzzleSize = size(cell{1},1) * size(cell{1},1);
%Defining initial parameters
trainingDataSize = size(trainingData,1);
validationDataSize = size(validationData,1);
alpha = 0.2;
hiddenLayerSize = 300;
%Random weights for each input node of the network mapped to a hidden layer
w1 = rand(puzzleSize,hiddenLayerSize);
b  = rand(puzzleSize,hiddenLayerSize);
%Random weights for each hidden layer to the output nodes [-k^2,k^2]
w2 = rand(hiddenLayerSize, puzzleSize);