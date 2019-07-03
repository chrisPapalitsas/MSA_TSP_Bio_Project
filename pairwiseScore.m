function [S,NoS,maxName,sumRow,maxLength] = pairwiseScore(seqs,readyScore,scoreFileName,distanceMatrix)

if readyScore==0
    size=length(seqs(1).Sequence);
    NoS=length(seqs);

    %below each seq is made of equal length
    maxLength = 0;
    maxName=0;
    for i=1:NoS
        if length(seqs(i).Sequence)>maxLength
        maxLength = length(seqs(i).Sequence);
        end

        if length(seqs(i).Header)>maxName
            maxName=length(seqs(i).Header);
        end
    end

    for i=1:NoS
        A(i,1:maxLength) = '-';
        A(i,1:length(seqs(i).Sequence))=seqs(i).Sequence;

    end

    S=zeros(NoS);
    %compute score matrix for each pair
    Score=zeros(NoS);
    z=1;
    x=0;
    while z<NoS
        j=z+1;
        while j<=NoS
            x=x+1; 
            Score(z,j)=sum(A(z,:)==A(j,:)& A(j,:) ~= '-');
            Score(z,j)=Score(z,j)- sum(A(z,:)~=A(j,:));
            %Score3(x)=sum(A(z,:)==A(j,i)& A(j,i) ~= '-');
            %Score3(x)=Score3(x)- sum(A(z,:)~=A(j,i));
            j=j+1;
        end
         z=z+1;
    end



    %complete the triangular matrices
    for i=1:NoS
        for j=1:NoS
           Score(j,i)=Score(i,j); 
           %ScoreAl(j,i)=ScoreAl(i,j);
        end   
    end




    dOpt=max(max(Score));
    %ScoreAlmin=max(max(ScoreAl));

    %distance matrix for both scores
    for i=1:NoS
        for j=1:NoS
            if i~=j
                S(i,j)=dOpt-Score(i,j)+1; 
                %SAl(i,j)=ScoreAlmin-ScoreAl(i,j)+1; 
            end
        end   
    end
    dlmwrite(distanceMatrix,S,'delimiter','\t','precision',5)

else
   S=dlmread(scoreFileName);
   NoS=length(S);
    maxName=NoS;
    maxLength=0;
end


sumRow=zeros(1,NoS);
for i=1:NoS
   sumRow(i) = sum(S(i,:));
end
end