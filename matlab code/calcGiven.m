function given = calcGiven(NoS,tspFileName,sumRow,choice)

given=zeros(1,NoS*2);

if choice=='random'
        for i=1:NoS
            given(i)=i;
        end
elseif choice=='sorted'
        [~,given] = sort(sumRow,'descend');%if we just sort the distances
elseif choice=='tspMod'
        tsp=dlmread(tspFileName);
        tsp=tsp';
        tsp=tsp+1;
        for i=1:NoS
            given(i)=tsp(i);
        end
end
    
    for i=NoS+1:NoS*2
    given(i)=i;
    end
end


