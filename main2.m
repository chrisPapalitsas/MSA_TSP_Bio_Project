clear variables;
fid = fopen('Real_Results_SumPairs2.txt', 'w');
fprintf(fid,'N \t Seq \t NoS \t Max Length \t UPGA \t single \t NeighborJoin \t Sorted \t TSP1 \t TSP2\n');

fid2 = fopen('Real_Results_SumMatches2.txt', 'w');
fprintf(fid2,'N \t Seq \t NoS \t Max Length \t UPGA \t single \t NeighborJoin \t Sorted \t TSP1 \t TSP2\n');

fid3 = fopen('Real_Results_Length2.txt', 'w');
fprintf(fid3,'N \t Seq \t NoS \t Max Length \t UPGA \t single \t NeighborJoin \t Sorted \t TSP1 \t TSP2\n');
   
for i=1:34

 
seqNum = sprintf( '%02d', i );
%read files
seqName=['data_Read/seqs/real/Real' seqNum '.faa'];
seqs=fastaread(seqName);

%seqs=fastaread('data_Read/seqs/writeTest.fa');

tspFileName='data_Read/tsp/S_distance_212_results.txt';
tspFileName2='data_Read/tsp/results_18_03_2.txt';
tspFileName3='data_Read/tsp/results_18_03_3.txt';
tspFileName4='data_Read/tsp/results_18_03_4.txt';
%tspFileName='data_Read/tsp/TSP12.txt';

%read Score file
scoreName=['data_Export/real/distance_Matrices2/Score' seqNum '.txt'];
scoreFileName=scoreName;

%name of write file of distance matrix
scoreNameWr=['data_Export/real/distance_Matrices2/Score' seqNum '.txt'];
distanceMatrix=scoreNameWr;


%parameters
choice='random';%the given mode, random, sorted, or tspMod
readyScore=1;%0 if we calculate the score or 1 if we read it
writeAlignedSeqs=0;%0 no write, 1 write
writeResult=1;%0 no write,1 write
writeTrees=0;%0 no write,1 write


maxLength=0;
%compute pairwise scores and write it
[S,NoS,maxName,sumRow,maxLength]=pairwiseScore(seqs,readyScore,scoreFileName,distanceMatrix);



%calculate the given matrix
given = calcGiven(NoS,tspFileName,sumRow,'random');
given2 = calcGiven(NoS,tspFileName,sumRow,'random');
given3 = calcGiven(NoS,tspFileName3,sumRow,choice);



%calculate our tree and write it
tr_1 = ourTree(maxName,NoS,seqs,given,writeTrees);
tr_2 = ourTree(maxName,NoS,seqs,given2,writeTrees);
tr_3 = ourTree(maxName,NoS,seqs,given3,writeTrees);
% tr_4 = ourTree(maxName,NoS,seqs,given4,writeTrees);


%calculate buildin tree and write it
buildinTree;
buildinTree2;

%calculate neighbor join tree and write it
neighborJoinTree;


%align the sequnces
%SeqsMultiAligned = multialign(seqs, tr_1,'ScoringMatrix','GONNET','GAPOPEN', 1);
SeqsMultiAligned2 = multialign(seqs, PhyloTree,'ScoringMatrix','GONNET','GAPOPEN', 1);
SeqsMultiAlignedb = multialign(seqs, PhyloTreeb,'ScoringMatrix','GONNET','GAPOPEN', 1);
SeqsMultiAligned3 = multialign(seqs, PhyloTree2,'ScoringMatrix','GONNET','GAPOPEN', 1);
SeqsMultiAligned = multialign(seqs, tr_1,'ScoringMatrix','GONNET','GAPOPEN', 1);
SeqsMultiAligned4 = multialign(seqs, tr_2,'ScoringMatrix','GONNET','GAPOPEN', 1);
SeqsMultiAligned5 = multialign(seqs, tr_3,'ScoringMatrix','GONNET','GAPOPEN', 1);
% SeqsMultiAligned6 = multialign(seqs, tr_4,'ScoringMatrix','GONNET','GAPOPEN', 1);


%calculate pairwise score
[sumPairs2,matches2] = sumOfPairs(SeqsMultiAligned2,NoS);
[sumPairsb,matchesb] = sumOfPairs(SeqsMultiAlignedb,NoS);
[sumPairs3,matches3] = sumOfPairs(SeqsMultiAligned3,NoS);
[sumPairs,matches] = sumOfPairs(SeqsMultiAligned,NoS);
[sumPairs4,matches4] = sumOfPairs(SeqsMultiAligned4,NoS);
[sumPairs5,matches5] = sumOfPairs(SeqsMultiAligned5,NoS);
% sumPairs6 = sumOfPairs(SeqsMultiAligned6,NoS);


%calculate pair length
l2=length(SeqsMultiAligned2(1).Sequence);
lb=length(SeqsMultiAlignedb(1).Sequence);
l3=length(SeqsMultiAligned3(1).Sequence);
l1=length(SeqsMultiAligned(1).Sequence);
l4=length(SeqsMultiAligned4(1).Sequence);
l5=length(SeqsMultiAligned5(1).Sequence);
% l6=length(SeqsMultiAligned6(1).Sequence);


%show results
fprintf('Using built-in tree: %d with length %d \n',sumPairs2,l2);
fprintf('Using built-in tree: %d with length %d \n',sumPairsb,l2);
fprintf('Using neighbor join: %d with length %d \n',sumPairs3,l3);
fprintf('%s \nUsing our tree: %d with length %d \n',choice,sumPairs,l1);
fprintf('%s \nUsing our tree: %d with length %d \n',choice,sumPairs4,l4);
fprintf('%s \nUsing our tree: %d with length %d \n',choice,sumPairs5,l5);
% fprintf('%s \nUsing our tree: %d with length %d \n',choice,sumPairs6,l6);


%write Score file
if writeResult==1
    %fid = fopen('Balibase_Results.txt', 'w');
    fprintf(fid,'%d \t %s \t %d \t %d \t %d \t %d \t %d \t %d \t %d \t %d\n',i,seqName,NoS,maxLength, sumPairs2,sumPairsb,sumPairs3,sumPairs,sumPairs4,sumPairs5);
    fprintf(fid2,'%d \t %s \t %d \t %d \t %d \t %d \t %d \t %d \t %d \t %d\n',i,seqName,NoS,maxLength, matches2,matchesb,matches3,matches,matches4,matches5);
    fprintf(fid3,'%d \t %s \t %d \t %d \t %d \t %d \t %d \t %d \t %d \t %d\n',i,seqName,NoS,maxLength, l2,lb,l3,l1,l4,l5);

%     fprintf(fid,'%s \nUsing our tree: %d with length %d \n',choice,sumPairs6,l6);
    %fclose(fid);
end


%write the aligned sequences
  if writeAlignedSeqs==1
    fastawrite('SeqsMultiAligned.fa',SeqsMultiAligned);
    % fastawrite('SeqsMultiAligned2.fa',SeqsMultiAligned2);
    % fastawrite('SeqsMultiAligned3.fa',SeqsMultiAligned3);
    % multialignwrite('p53.msf',SeqsMultiAligned)
  end
  
end
fclose(fid);
fclose(fid2);
fclose(fid3);