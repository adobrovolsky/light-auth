package dobrovolsky.server.domain.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListMapper<FROM, TO> implements Mapper<List<FROM>, List<TO>> {

    private Mapper<FROM, TO> mapper;

    public ListMapper(Mapper<FROM, TO> mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<TO> map(List<FROM> models) {
        if (models == null) {
            return Collections.emptyList();
        }
        List<TO> list = new ArrayList<>(models.size());
        for (FROM from : models) {
            list.add(mapper.map(from));
        }
        return list;
    }
}
