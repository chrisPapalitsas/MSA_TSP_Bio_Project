clear all;


numberOfLetters=24;
mysmat = eye(numberOfLetters);
mysmat=mysmat*2;
mysmat=mysmat-1;

seqs=fastaread('data_Read/seqs/clustalOmega00039_2.fa');
size=length(seqs(1).Sequence);
NoS=length(seqs);

SeqsMultiAligned = seqs;

sumPairs=0;
sumMatches=0;
neg=0;
for i=1:NoS
    j=i+1;
    while j<=NoS
        sumMatches=sum(SeqsMultiAligned(i).Sequence == SeqsMultiAligned(j).Sequence & SeqsMultiAligned(i).Sequence ~= '-');
        neg=sum(SeqsMultiAligned(i).Sequence ~= SeqsMultiAligned(j).Sequence);
        sumPairs=sumPairs+sumMatches-neg;
        j=j+1;
    end
    sumMatches=0;
    neg=0;
end

%showalignment(SeqsMultiAligned)
l1=length(SeqsMultiAligned(1).Sequence);
fprintf('Using Clustal: %d with length %d \n',sumPairs,l1);


clear all;


numberOfLetters=24;
mysmat = eye(numberOfLetters);
mysmat=mysmat*2;
mysmat=mysmat-1;

seqs=fastaread('clustal0039.fa');
size=length(seqs(1).Sequence);
NoS=length(seqs);


SeqsMultiAligned = seqs;

sumPairs=0;
sumMatches=0;
neg=0;
for i=1:NoS
    j=i+1;
    while j<=NoS
        sumMatches=sum(SeqsMultiAligned(i).Sequence == SeqsMultiAligned(j).Sequence & SeqsMultiAligned(i).Sequence ~= '-');
        neg=sum(SeqsMultiAligned(i).Sequence ~= SeqsMultiAligned(j).Sequence);
        sumPairs=sumPairs+sumMatches-neg;
        j=j+1;
    end
    sumMatches=0;
    neg=0;
end

%showalignment(SeqsMultiAligned)
l1=length(SeqsMultiAligned(1).Sequence);
fprintf('Using Kalign: %d with length %d \n',sumPairs,l1);
