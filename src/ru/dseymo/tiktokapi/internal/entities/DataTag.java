package ru.dseymo.tiktokapi.internal.entities;

import lombok.Getter;
import ru.dseymo.tiktokapi.api.entities.Tag;

public class DataTag implements Tag {
	@Getter
	public String id, title;
}
