function tr_1 = ourTree(maxName,NoS,seqs,given,writeTrees)
   
givenNames=char([NoS,maxName]);
for i=1:NoS
%for j=1:length(seqs(1).Header)
    givenNames(i,1:length(seqs(i).Header))=seqs(i).Header;
%end
end

branches=NoS;
%b=zeros(branches-1,2);
for i=1:(branches-1)
    for j=1:2
        if i==1
        b(i,j)=given(i*j);
        else
            if j==1
                b(i,j)=given(i+j);
            end
            if j==2
                b(i,j)=given(branches-1+i);
            end
        end
    end
end

tr_1 = phytree(b,cellstr(givenNames)');

if writeTrees==1
    phytreewrite('mytree.tree',tr_1);
end

end