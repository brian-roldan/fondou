package nz.co.fondou.command.converter;

public interface CommandConverter<C, E> {

	public C toCommand(E entity);
	public E toEntity(C command);
	
}
