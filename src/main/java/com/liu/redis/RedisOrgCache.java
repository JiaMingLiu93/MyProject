package com.liu.redis;

/**
 * Created by Jam on 2017/5/15.
*/
public class RedisOrgCache{
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    private OrgTreeRedisDao treeNodeBaseRedisDao;
//
//    private OrgUserRedisDao orgUsersBaseRedisDao;
//
//    @PostConstruct
//    public void init(){
//        treeNodeBaseRedisDao = new OrgTreeRedisDao(redisTemplate);
//        orgUsersBaseRedisDao = new OrgUserRedisDao(redisTemplate);
//        //testDao = new TestDao(redisTemplate);
//    }
//
//
//    public void setTreeNode(String key, String node){
//        treeNodeBaseRedisDao.set(key,node);
//    }
//
//    public void setOrgUsers(String key,HashMap<Long,String> orgUsers){
//        orgUsersBaseRedisDao.setAll(key,orgUsers);
//    }
//
//    public String getOrgTree(String key){
//        return treeNodeBaseRedisDao.get(key);
//    }
//
//    public HashMap<Long,String> getOrgUsers(String key){
//        return orgUsersBaseRedisDao.getMap(key);
//    }
//
//
//    class OrgTreeRedisDao extends BaseRedisDao<String,String>{
//        public OrgTreeRedisDao(RedisTemplate redisTemplate) {
//            super(redisTemplate);
//        }
//    }
//
//    class OrgUserRedisDao extends BaseRedisDao<Long,String>{
//        public OrgUserRedisDao(RedisTemplate redisTemplate) {
//            super(redisTemplate);
//        }
//    }
//
//    class BaseRedisDao<HK, HV> implements InitializingBean{
//
//        //实际参数的class start
//        private Class<HK> hkClass;
//
//        private Class<HV> hvClass;
//
//        private Class<HK> getHKClass(){
//            if (hkClass == null) {
//                hkClass = (Class<HK>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
//            }
//            return hkClass;
//        }
//
//        private Class<HV> getHVClass(){
//            if (hvClass == null) {
//                hvClass = (Class<HV>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
//            }
//            return hvClass;
//        }
//        // end
//        private RedisTemplate<String, HV> redisTemplate;
//
//        public BaseRedisDao(RedisTemplate redisTemplate){
//            this.redisTemplate = redisTemplate;
//            if(getHKClass() == null || getHVClass() == null){
//                throw new IllegalArgumentException("获取泛型class失败");
//            }
//            //
//            valueOperations = redisTemplate.opsForValue();
//            hashOperations = redisTemplate.opsForHash();
//            listOperations = redisTemplate.opsForList();
//            setOperations = redisTemplate.opsForSet();
//            //
//            redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(getHVClass()));
//            redisTemplate.setHashKeySerializer(new Jackson2JsonRedisSerializer<>(getHKClass()));
//            redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(getHVClass()));
//        }
//
//        protected ValueOperations<String, HV> valueOperations;
//        //
//        protected HashOperations<String, HK, HV> hashOperations;
//        //
//        protected ListOperations<String, HV> listOperations;
//
//        protected SetOperations<String, HV> setOperations;
//        /**
//         *
//         * @param key
//         * @param value
//         * @param expire
//         * @return
//         */
//        public void set(String key, HV value, long expire) {
//            valueOperations.set(key, value, expire, TimeUnit.SECONDS);
//        }
//
//        public void set(String key,HV value){
//            valueOperations.set(key,value);
//        }
//
//        public void set(String key,HK hKey,HV value){
//            hashOperations.put(key,hKey,value);
//        }
//
//        public void setAll(String key, HashMap<HK,HV> map){
//            hashOperations.putAll(key,map);
//        }
//
//        /**
//         * get value
//         *
//         * @param key
//         * @return
//         */
//        public HV get(String key) {
//            return valueOperations.get(key);
//        }
//
//        public HV get(String key,Object hKey){
//            return hashOperations.get(key,hKey);
//        }
//
//        protected Set<HK> getHKeys(String key){
//            return hashOperations.keys(key);
//        }
//
//        public HashMap<HK,HV> getMap(String key){
//            Set<HK> hKeys = getHKeys(key);
//            return hKeys.stream().map(hk -> {
//                HV hv = hashOperations.get(key, (HK) hk);
//                return Maps.immutableEntry(hk,hv);
//            }).collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(a,b)->a,HashMap::new));
//        }
//
//        /**
//         * key delete
//         * @param key
//         */
//        protected void delete(String key){
//            getRedisTemplate().delete(key);
//        }
//
//        /**
//         * key exist
//         * @param key
//         * @return
//         */
//        protected boolean hasKey(String key){
//            return  getRedisTemplate().hasKey(key);
//        }
//        /**
//         *key expire
//         * @param key
//         * @param timeout
//         * @param unit
//         * @return
//         */
//        protected Boolean expire(String key,long timeout,TimeUnit unit){
//            return getRedisTemplate().expire(key, timeout, unit);
//        }
//        /**
//         * redistemplate是全局唯一的，子类不要出现对redistemplate的成员变量的设置(比如keyserializer,)
//         * @return
//         */
//        RedisTemplate<String, HV> getRedisTemplate() {
//            return redisTemplate;
//        }
//        /**
//         * 当需要更改serializer,可以直接通过connection.set等方法实现
//         * @param callback
//         * @return
//         */
//        protected <T> T execute(RedisCallback<T> callback){
//            return redisTemplate.execute(callback);
//        }
//        /**
//         * 获取stringserializer
//         */
//        protected RedisSerializer<String> getStringSerializer(){
//            return redisTemplate.getStringSerializer();
//        }
//        /**
//         * 获取JdkSerializationRedisSerializer
//         */
//        @SuppressWarnings("unchecked")
//        protected <T> RedisSerializer<T> getDefaultSerializer(){
//            return (RedisSerializer<T>) redisTemplate.getDefaultSerializer();
//        }
//        /**
//         * 获取stringserializer
//         * @return
//         */
//        @SuppressWarnings("unchecked")
//        protected  RedisSerializer<String> getKeySerializer(){
//            return (RedisSerializer<String>) redisTemplate.getKeySerializer();
//        }
//        /**
//         * 获取jackson2jsonredisserializer
//         * @return
//         */
//        protected RedisSerializer<HV> getValueSerializer(){
//            return (RedisSerializer<HV>) redisTemplate.getValueSerializer();
//        }
//        /**
//         * 获取jackson2jsonredisserializer
//         * @return
//         */
//        @SuppressWarnings("unchecked")
//        protected RedisSerializer<HK> getHashKeySerializer() {
//            return (RedisSerializer<HK>) redisTemplate.getHashKeySerializer();
//        }
//
//        /**
//         * 获取jackson2jsonredisserializer
//         * @return
//         */
//        @SuppressWarnings("unchecked")
//        protected RedisSerializer<HV> getHashValueSerializer() {
//            return (RedisSerializer<HV>) redisTemplate.getHashValueSerializer();
//        }
//
//        @Override
//        public void afterPropertiesSet() throws Exception {
//            System.out.println("in the initializeBean.");
//            //if(getHKClass() == null || getHVClass() == null){
//            //    throw new IllegalArgumentException("获取泛型class失败");
//            //}
//            ////
//            //valueOperations = redisTemplate.opsForValue();
//            //hashOperations = redisTemplate.opsForHash();
//            //listOperations = redisTemplate.opsForList();
//            //setOperations = redisTemplate.opsForSet();
//            ////
//            //redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<HV>(getHVClass()));
//            //redisTemplate.setHashKeySerializer(new Jackson2JsonRedisSerializer<HK>(getHKClass()));
//            //redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<HV>(getHVClass()));
//        }
//    }
}
