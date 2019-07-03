PhyloTree2 = seqneighjoin(DD,'equivar',seqs);
SeqsMultiAligned3 = multialign(seqs, PhyloTree2,'ScoringMatrix','GONNET','GAPOPEN', 1);
if writeTrees==1
    phytreewrite('neighborjoin.tree',PhyloTree2);
end
