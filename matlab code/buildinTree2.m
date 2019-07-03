DD = seqpdist(seqs);
PhyloTreeb = seqlinkage(DD,'single',seqs);%single
SeqsMultiAlignedb = multialign(seqs, PhyloTreeb,'ScoringMatrix','GONNET','GAPOPEN', 1);
if writeTrees==1
    phytreewrite('defaultt.tree',PhyloTreeb);
end