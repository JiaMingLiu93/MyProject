package com.liu.service;

import java.io.InputStream;
import java.util.Collection;

/**
 * Created by Jam on 2017/2/13.
 */
public interface ParserService<E> {
   Collection<E> parse(InputStream inputStream);
}
