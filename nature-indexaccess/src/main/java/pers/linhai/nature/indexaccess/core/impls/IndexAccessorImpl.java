/*
 * 项 目 名:  Storage Tool Service V100R001C00
 * 文 件 名:  pers.linhai.esframework.core.impls.IndexAccessorImpl.java
 * 版       权:  XXX Technologies Co., Ltd. Copyright 2017,  All rights reserved.
 * 描       述:  XXX PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * 修 改 人:  shinelon
 * 修改时间:  2017年6月11日
 * 修改内容:  创建
 */
package pers.linhai.nature.indexaccess.core.impls;

import pers.linhai.nature.indexaccess.core.AccessorFactory;
import pers.linhai.nature.indexaccess.core.processor.IndicesAdminClientProcessor;
import pers.linhai.nature.indexaccess.interfaces.IndexAccessor;
import pers.linhai.nature.indexaccess.interfaces.TypeAccessorInitialization;
import pers.linhai.nature.indexaccess.model.index.Index;

/**
 * 索引访问器实现
 * @author  shinelon
 * @version  V100R001C00
 */
@SuppressWarnings("unchecked")
public class IndexAccessorImpl<T extends Index> implements IndexAccessor<T>
{
    
    private static final TypeAccessorInitialization DEFAULT_TYPE_ACCESSOR_INITIALIZATION = new DefaultTypeAccessInitializationImpl();
    
    private T index;
    
    private IndicesAdminClientProcessor indicesAdminClientProcessor;
    
    /** 
     * <默认构造函数>
     *
     * @param index
     */
    public IndexAccessorImpl(T index)
    {
        this(index, DEFAULT_TYPE_ACCESSOR_INITIALIZATION);
    }
    
    /** 
     * <默认构造函数>
     *
     * @param index T
     * @param typeAccessorInitialization TypeAccessorInitialization
     */
    public IndexAccessorImpl(T index, TypeAccessorInitialization typeAccessorInitialization)
    {
        this.index = index;
        indicesAdminClientProcessor = IndicesAdminClientProcessor.newInstance(index, typeAccessorInitialization);
        AccessorFactory.add((Class<T>)index.getClass(), this);
    }
    
    //删除索引
    public void delete()
    {
        indicesAdminClientProcessor.delete();
    }
    
    /**
     * 判断索引库是否存在
     * @return boolean
     */
    public boolean exists()
    {
        return indicesAdminClientProcessor.exists();
    }
    
    /**
     * 刷出缓冲区
     * void
     */
    public void flush()
    {
        indicesAdminClientProcessor.flush();
    }
    
    /**
     * 刷新索引
     * void
     */
    public void refresh()
    {
        indicesAdminClientProcessor.refresh();
    }

    /**
     * 返回 index
     *
     * @return index
     */
    public T index()
    {
        return index;
    }
}
