   
for i=1:218

 
seqNum = sprintf( '%04d', i );
%read files
seqName=['data_Read/seqs/ref10/BBA' seqNum '.tfa'];
seqs=fastaread(seqName);

%seqs=fastaread('data_Read/seqs/writeTest.fa');

tspFileName='data_Read/tsp/S_distance_212_results.txt';
tspFileName2='data_Read/tsp/results_18_03_2.txt';
tspFileName3='data_Read/tsp/results_18_03_3.txt';
tspFileName4='data_Read/tsp/results_18_03_4.txt';
%tspFileName='data_Read/tsp/TSP12.txt';

%read Score file
scoreName=['data_Read/scores/Score' seqNum '.txt'];
scoreFileName=scoreName;

%name of write file of distance matrix
scoreNameWr=['data_Export/ref10/distance_Matrices/Score' seqNum '.txt'];
distanceMatrix=scoreNameWr;


%parameters
choice='sorted';%the given mode, random, sorted, or tspMod
readyScore=0;%0 if we calculate the score or 1 if we read it
writeAlignedSeqs=0;%0 no write, 1 write
writeResult=1;%0 no write,1 write
writeTrees=0;%0 no write,1 write



%compute pairwise scores and write it
[S,NoS,maxName,sumRow,maxLength]=pairwiseScore(seqs,readyScore,scoreFileName,distanceMatrix);
end