package dobrovolsky.server.domain.mapper;

public interface Mapper<FROM, TO> {

    TO map(FROM from);
}
