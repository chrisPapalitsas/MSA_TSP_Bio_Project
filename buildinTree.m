DD = seqpdist(seqs);
PhyloTree = seqlinkage(DD,'average',seqs);%single
SeqsMultiAligned2 = multialign(seqs, PhyloTree,'ScoringMatrix','GONNET','GAPOPEN', 1);
if writeTrees==1
    phytreewrite('defaultt.tree',PhyloTree);
end