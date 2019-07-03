function [sumPairs,matches] = sumOfPairs(SeqsMultiAligned,NoS)
   
sumPairs=0;
sumMatches=0;
neg=0;
pairs=0;
matches=0;
for i=1:NoS
    j=i+1;
    while j<=NoS
        sumMatches=sum(SeqsMultiAligned(i).Sequence == SeqsMultiAligned(j).Sequence & SeqsMultiAligned(i).Sequence ~= '-');
        neg=sum(SeqsMultiAligned(i).Sequence ~= SeqsMultiAligned(j).Sequence);
        sumPairs=sumPairs+sumMatches-neg;
        matches=matches+sumMatches;
        j=j+1;
        pairs=pairs+length(SeqsMultiAligned(i).Sequence);
    end
    sumMatches=0;
    neg=0;
end

end