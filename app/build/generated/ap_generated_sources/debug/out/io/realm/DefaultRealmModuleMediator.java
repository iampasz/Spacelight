package io.realm;


import android.util.JsonReader;
import io.realm.ImportFlag;
import io.realm.internal.ColumnInfo;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.RealmProxyMediator;
import io.realm.internal.Row;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

@io.realm.annotations.RealmModule
class DefaultRealmModuleMediator extends RealmProxyMediator {

    private static final Set<Class<? extends RealmModel>> MODEL_CLASSES;
    static {
        Set<Class<? extends RealmModel>> modelClasses = new HashSet<Class<? extends RealmModel>>(5);
        modelClasses.add(com.appsforkids.pasz.spacelight.RealmObjects.MySettings.class);
        modelClasses.add(com.appsforkids.pasz.spacelight.RealmObjects.LottieImage.class);
        modelClasses.add(com.appsforkids.pasz.spacelight.RealmObjects.ImageFile.class);
        modelClasses.add(com.appsforkids.pasz.spacelight.RealmObjects.AudioFile.class);
        modelClasses.add(com.appsforkids.pasz.spacelight.Models.Nightlight.class);
        MODEL_CLASSES = Collections.unmodifiableSet(modelClasses);
    }

    @Override
    public Map<Class<? extends RealmModel>, OsObjectSchemaInfo> getExpectedObjectSchemaInfoMap() {
        Map<Class<? extends RealmModel>, OsObjectSchemaInfo> infoMap = new HashMap<Class<? extends RealmModel>, OsObjectSchemaInfo>(5);
        infoMap.put(com.appsforkids.pasz.spacelight.RealmObjects.MySettings.class, io.realm.com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.appsforkids.pasz.spacelight.RealmObjects.LottieImage.class, io.realm.com_appsforkids_pasz_spacelight_RealmObjects_LottieImageRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.appsforkids.pasz.spacelight.RealmObjects.ImageFile.class, io.realm.com_appsforkids_pasz_spacelight_RealmObjects_ImageFileRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.appsforkids.pasz.spacelight.RealmObjects.AudioFile.class, io.realm.com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.appsforkids.pasz.spacelight.Models.Nightlight.class, io.realm.com_appsforkids_pasz_spacelight_Models_NightlightRealmProxy.getExpectedObjectSchemaInfo());
        return infoMap;
    }

    @Override
    public ColumnInfo createColumnInfo(Class<? extends RealmModel> clazz, OsSchemaInfo schemaInfo) {
        checkClass(clazz);

        if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.MySettings.class)) {
            return io.realm.com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.LottieImage.class)) {
            return io.realm.com_appsforkids_pasz_spacelight_RealmObjects_LottieImageRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.ImageFile.class)) {
            return io.realm.com_appsforkids_pasz_spacelight_RealmObjects_ImageFileRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.AudioFile.class)) {
            return io.realm.com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.appsforkids.pasz.spacelight.Models.Nightlight.class)) {
            return io.realm.com_appsforkids_pasz_spacelight_Models_NightlightRealmProxy.createColumnInfo(schemaInfo);
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public String getSimpleClassNameImpl(Class<? extends RealmModel> clazz) {
        checkClass(clazz);

        if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.MySettings.class)) {
            return "MySettings";
        }
        if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.LottieImage.class)) {
            return "LottieImage";
        }
        if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.ImageFile.class)) {
            return "ImageFile";
        }
        if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.AudioFile.class)) {
            return "AudioFile";
        }
        if (clazz.equals(com.appsforkids.pasz.spacelight.Models.Nightlight.class)) {
            return "Nightlight";
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public Class<? extends RealmModel> getClazzImpl(String className) {
        checkClassName(className);

        if (className.equals("MySettings")) {
            return com.appsforkids.pasz.spacelight.RealmObjects.MySettings.class;
        }
        if (className.equals("LottieImage")) {
            return com.appsforkids.pasz.spacelight.RealmObjects.LottieImage.class;
        }
        if (className.equals("ImageFile")) {
            return com.appsforkids.pasz.spacelight.RealmObjects.ImageFile.class;
        }
        if (className.equals("AudioFile")) {
            return com.appsforkids.pasz.spacelight.RealmObjects.AudioFile.class;
        }
        if (className.equals("Nightlight")) {
            return com.appsforkids.pasz.spacelight.Models.Nightlight.class;
        }
        throw getMissingProxyClassException(className);
    }

    @Override
    public boolean hasPrimaryKeyImpl(Class<? extends RealmModel> clazz) {
        return false;
    }

    @Override
    public <E extends RealmModel> E newInstance(Class<E> clazz, Object baseRealm, Row row, ColumnInfo columnInfo, boolean acceptDefaultValue, List<String> excludeFields) {
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        try {
            objectContext.set((BaseRealm) baseRealm, row, columnInfo, acceptDefaultValue, excludeFields);
            checkClass(clazz);

            if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.MySettings.class)) {
                return clazz.cast(new io.realm.com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxy());
            }
            if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.LottieImage.class)) {
                return clazz.cast(new io.realm.com_appsforkids_pasz_spacelight_RealmObjects_LottieImageRealmProxy());
            }
            if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.ImageFile.class)) {
                return clazz.cast(new io.realm.com_appsforkids_pasz_spacelight_RealmObjects_ImageFileRealmProxy());
            }
            if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.AudioFile.class)) {
                return clazz.cast(new io.realm.com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxy());
            }
            if (clazz.equals(com.appsforkids.pasz.spacelight.Models.Nightlight.class)) {
                return clazz.cast(new io.realm.com_appsforkids_pasz_spacelight_Models_NightlightRealmProxy());
            }
            throw getMissingProxyClassException(clazz);
        } finally {
            objectContext.clear();
        }
    }

    @Override
    public Set<Class<? extends RealmModel>> getModelClasses() {
        return MODEL_CLASSES;
    }

    @Override
    public <E extends RealmModel> E copyOrUpdate(Realm realm, E obj, boolean update, Map<RealmModel, RealmObjectProxy> cache, Set<ImportFlag> flags) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<E> clazz = (Class<E>) ((obj instanceof RealmObjectProxy) ? obj.getClass().getSuperclass() : obj.getClass());

        if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.MySettings.class)) {
            com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxy.MySettingsColumnInfo columnInfo = (com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxy.MySettingsColumnInfo) realm.getSchema().getColumnInfo(com.appsforkids.pasz.spacelight.RealmObjects.MySettings.class);
            return clazz.cast(io.realm.com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxy.copyOrUpdate(realm, columnInfo, (com.appsforkids.pasz.spacelight.RealmObjects.MySettings) obj, update, cache, flags));
        }
        if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.LottieImage.class)) {
            com_appsforkids_pasz_spacelight_RealmObjects_LottieImageRealmProxy.LottieImageColumnInfo columnInfo = (com_appsforkids_pasz_spacelight_RealmObjects_LottieImageRealmProxy.LottieImageColumnInfo) realm.getSchema().getColumnInfo(com.appsforkids.pasz.spacelight.RealmObjects.LottieImage.class);
            return clazz.cast(io.realm.com_appsforkids_pasz_spacelight_RealmObjects_LottieImageRealmProxy.copyOrUpdate(realm, columnInfo, (com.appsforkids.pasz.spacelight.RealmObjects.LottieImage) obj, update, cache, flags));
        }
        if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.ImageFile.class)) {
            com_appsforkids_pasz_spacelight_RealmObjects_ImageFileRealmProxy.ImageFileColumnInfo columnInfo = (com_appsforkids_pasz_spacelight_RealmObjects_ImageFileRealmProxy.ImageFileColumnInfo) realm.getSchema().getColumnInfo(com.appsforkids.pasz.spacelight.RealmObjects.ImageFile.class);
            return clazz.cast(io.realm.com_appsforkids_pasz_spacelight_RealmObjects_ImageFileRealmProxy.copyOrUpdate(realm, columnInfo, (com.appsforkids.pasz.spacelight.RealmObjects.ImageFile) obj, update, cache, flags));
        }
        if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.AudioFile.class)) {
            com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxy.AudioFileColumnInfo columnInfo = (com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxy.AudioFileColumnInfo) realm.getSchema().getColumnInfo(com.appsforkids.pasz.spacelight.RealmObjects.AudioFile.class);
            return clazz.cast(io.realm.com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxy.copyOrUpdate(realm, columnInfo, (com.appsforkids.pasz.spacelight.RealmObjects.AudioFile) obj, update, cache, flags));
        }
        if (clazz.equals(com.appsforkids.pasz.spacelight.Models.Nightlight.class)) {
            com_appsforkids_pasz_spacelight_Models_NightlightRealmProxy.NightlightColumnInfo columnInfo = (com_appsforkids_pasz_spacelight_Models_NightlightRealmProxy.NightlightColumnInfo) realm.getSchema().getColumnInfo(com.appsforkids.pasz.spacelight.Models.Nightlight.class);
            return clazz.cast(io.realm.com_appsforkids_pasz_spacelight_Models_NightlightRealmProxy.copyOrUpdate(realm, columnInfo, (com.appsforkids.pasz.spacelight.Models.Nightlight) obj, update, cache, flags));
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public long insert(Realm realm, RealmModel object, Map<RealmModel, Long> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((object instanceof RealmObjectProxy) ? object.getClass().getSuperclass() : object.getClass());

        if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.MySettings.class)) {
            return io.realm.com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxy.insert(realm, (com.appsforkids.pasz.spacelight.RealmObjects.MySettings) object, cache);
        } else if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.LottieImage.class)) {
            return io.realm.com_appsforkids_pasz_spacelight_RealmObjects_LottieImageRealmProxy.insert(realm, (com.appsforkids.pasz.spacelight.RealmObjects.LottieImage) object, cache);
        } else if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.ImageFile.class)) {
            return io.realm.com_appsforkids_pasz_spacelight_RealmObjects_ImageFileRealmProxy.insert(realm, (com.appsforkids.pasz.spacelight.RealmObjects.ImageFile) object, cache);
        } else if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.AudioFile.class)) {
            return io.realm.com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxy.insert(realm, (com.appsforkids.pasz.spacelight.RealmObjects.AudioFile) object, cache);
        } else if (clazz.equals(com.appsforkids.pasz.spacelight.Models.Nightlight.class)) {
            return io.realm.com_appsforkids_pasz_spacelight_Models_NightlightRealmProxy.insert(realm, (com.appsforkids.pasz.spacelight.Models.Nightlight) object, cache);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public void insert(Realm realm, Collection<? extends RealmModel> objects) {
        Iterator<? extends RealmModel> iterator = objects.iterator();
        RealmModel object = null;
        Map<RealmModel, Long> cache = new HashMap<RealmModel, Long>(objects.size());
        if (iterator.hasNext()) {
            //  access the first element to figure out the clazz for the routing below
            object = iterator.next();
            // This cast is correct because obj is either
            // generated by RealmProxy or the original type extending directly from RealmObject
            @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((object instanceof RealmObjectProxy) ? object.getClass().getSuperclass() : object.getClass());

            if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.MySettings.class)) {
                io.realm.com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxy.insert(realm, (com.appsforkids.pasz.spacelight.RealmObjects.MySettings) object, cache);
            } else if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.LottieImage.class)) {
                io.realm.com_appsforkids_pasz_spacelight_RealmObjects_LottieImageRealmProxy.insert(realm, (com.appsforkids.pasz.spacelight.RealmObjects.LottieImage) object, cache);
            } else if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.ImageFile.class)) {
                io.realm.com_appsforkids_pasz_spacelight_RealmObjects_ImageFileRealmProxy.insert(realm, (com.appsforkids.pasz.spacelight.RealmObjects.ImageFile) object, cache);
            } else if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.AudioFile.class)) {
                io.realm.com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxy.insert(realm, (com.appsforkids.pasz.spacelight.RealmObjects.AudioFile) object, cache);
            } else if (clazz.equals(com.appsforkids.pasz.spacelight.Models.Nightlight.class)) {
                io.realm.com_appsforkids_pasz_spacelight_Models_NightlightRealmProxy.insert(realm, (com.appsforkids.pasz.spacelight.Models.Nightlight) object, cache);
            } else {
                throw getMissingProxyClassException(clazz);
            }
            if (iterator.hasNext()) {
                if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.MySettings.class)) {
                    io.realm.com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.LottieImage.class)) {
                    io.realm.com_appsforkids_pasz_spacelight_RealmObjects_LottieImageRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.ImageFile.class)) {
                    io.realm.com_appsforkids_pasz_spacelight_RealmObjects_ImageFileRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.AudioFile.class)) {
                    io.realm.com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.appsforkids.pasz.spacelight.Models.Nightlight.class)) {
                    io.realm.com_appsforkids_pasz_spacelight_Models_NightlightRealmProxy.insert(realm, iterator, cache);
                } else {
                    throw getMissingProxyClassException(clazz);
                }
            }
        }
    }

    @Override
    public long insertOrUpdate(Realm realm, RealmModel obj, Map<RealmModel, Long> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((obj instanceof RealmObjectProxy) ? obj.getClass().getSuperclass() : obj.getClass());

        if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.MySettings.class)) {
            return io.realm.com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxy.insertOrUpdate(realm, (com.appsforkids.pasz.spacelight.RealmObjects.MySettings) obj, cache);
        } else if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.LottieImage.class)) {
            return io.realm.com_appsforkids_pasz_spacelight_RealmObjects_LottieImageRealmProxy.insertOrUpdate(realm, (com.appsforkids.pasz.spacelight.RealmObjects.LottieImage) obj, cache);
        } else if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.ImageFile.class)) {
            return io.realm.com_appsforkids_pasz_spacelight_RealmObjects_ImageFileRealmProxy.insertOrUpdate(realm, (com.appsforkids.pasz.spacelight.RealmObjects.ImageFile) obj, cache);
        } else if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.AudioFile.class)) {
            return io.realm.com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxy.insertOrUpdate(realm, (com.appsforkids.pasz.spacelight.RealmObjects.AudioFile) obj, cache);
        } else if (clazz.equals(com.appsforkids.pasz.spacelight.Models.Nightlight.class)) {
            return io.realm.com_appsforkids_pasz_spacelight_Models_NightlightRealmProxy.insertOrUpdate(realm, (com.appsforkids.pasz.spacelight.Models.Nightlight) obj, cache);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public void insertOrUpdate(Realm realm, Collection<? extends RealmModel> objects) {
        Iterator<? extends RealmModel> iterator = objects.iterator();
        RealmModel object = null;
        Map<RealmModel, Long> cache = new HashMap<RealmModel, Long>(objects.size());
        if (iterator.hasNext()) {
            //  access the first element to figure out the clazz for the routing below
            object = iterator.next();
            // This cast is correct because obj is either
            // generated by RealmProxy or the original type extending directly from RealmObject
            @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((object instanceof RealmObjectProxy) ? object.getClass().getSuperclass() : object.getClass());

            if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.MySettings.class)) {
                io.realm.com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxy.insertOrUpdate(realm, (com.appsforkids.pasz.spacelight.RealmObjects.MySettings) object, cache);
            } else if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.LottieImage.class)) {
                io.realm.com_appsforkids_pasz_spacelight_RealmObjects_LottieImageRealmProxy.insertOrUpdate(realm, (com.appsforkids.pasz.spacelight.RealmObjects.LottieImage) object, cache);
            } else if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.ImageFile.class)) {
                io.realm.com_appsforkids_pasz_spacelight_RealmObjects_ImageFileRealmProxy.insertOrUpdate(realm, (com.appsforkids.pasz.spacelight.RealmObjects.ImageFile) object, cache);
            } else if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.AudioFile.class)) {
                io.realm.com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxy.insertOrUpdate(realm, (com.appsforkids.pasz.spacelight.RealmObjects.AudioFile) object, cache);
            } else if (clazz.equals(com.appsforkids.pasz.spacelight.Models.Nightlight.class)) {
                io.realm.com_appsforkids_pasz_spacelight_Models_NightlightRealmProxy.insertOrUpdate(realm, (com.appsforkids.pasz.spacelight.Models.Nightlight) object, cache);
            } else {
                throw getMissingProxyClassException(clazz);
            }
            if (iterator.hasNext()) {
                if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.MySettings.class)) {
                    io.realm.com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.LottieImage.class)) {
                    io.realm.com_appsforkids_pasz_spacelight_RealmObjects_LottieImageRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.ImageFile.class)) {
                    io.realm.com_appsforkids_pasz_spacelight_RealmObjects_ImageFileRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.AudioFile.class)) {
                    io.realm.com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.appsforkids.pasz.spacelight.Models.Nightlight.class)) {
                    io.realm.com_appsforkids_pasz_spacelight_Models_NightlightRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else {
                    throw getMissingProxyClassException(clazz);
                }
            }
        }
    }

    @Override
    public <E extends RealmModel> E createOrUpdateUsingJsonObject(Class<E> clazz, Realm realm, JSONObject json, boolean update)
        throws JSONException {
        checkClass(clazz);

        if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.MySettings.class)) {
            return clazz.cast(io.realm.com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.LottieImage.class)) {
            return clazz.cast(io.realm.com_appsforkids_pasz_spacelight_RealmObjects_LottieImageRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.ImageFile.class)) {
            return clazz.cast(io.realm.com_appsforkids_pasz_spacelight_RealmObjects_ImageFileRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.AudioFile.class)) {
            return clazz.cast(io.realm.com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.appsforkids.pasz.spacelight.Models.Nightlight.class)) {
            return clazz.cast(io.realm.com_appsforkids_pasz_spacelight_Models_NightlightRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public <E extends RealmModel> E createUsingJsonStream(Class<E> clazz, Realm realm, JsonReader reader)
        throws IOException {
        checkClass(clazz);

        if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.MySettings.class)) {
            return clazz.cast(io.realm.com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.LottieImage.class)) {
            return clazz.cast(io.realm.com_appsforkids_pasz_spacelight_RealmObjects_LottieImageRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.ImageFile.class)) {
            return clazz.cast(io.realm.com_appsforkids_pasz_spacelight_RealmObjects_ImageFileRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.AudioFile.class)) {
            return clazz.cast(io.realm.com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.appsforkids.pasz.spacelight.Models.Nightlight.class)) {
            return clazz.cast(io.realm.com_appsforkids_pasz_spacelight_Models_NightlightRealmProxy.createUsingJsonStream(realm, reader));
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public <E extends RealmModel> E createDetachedCopy(E realmObject, int maxDepth, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<E> clazz = (Class<E>) realmObject.getClass().getSuperclass();

        if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.MySettings.class)) {
            return clazz.cast(io.realm.com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxy.createDetachedCopy((com.appsforkids.pasz.spacelight.RealmObjects.MySettings) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.LottieImage.class)) {
            return clazz.cast(io.realm.com_appsforkids_pasz_spacelight_RealmObjects_LottieImageRealmProxy.createDetachedCopy((com.appsforkids.pasz.spacelight.RealmObjects.LottieImage) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.ImageFile.class)) {
            return clazz.cast(io.realm.com_appsforkids_pasz_spacelight_RealmObjects_ImageFileRealmProxy.createDetachedCopy((com.appsforkids.pasz.spacelight.RealmObjects.ImageFile) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.AudioFile.class)) {
            return clazz.cast(io.realm.com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxy.createDetachedCopy((com.appsforkids.pasz.spacelight.RealmObjects.AudioFile) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.appsforkids.pasz.spacelight.Models.Nightlight.class)) {
            return clazz.cast(io.realm.com_appsforkids_pasz_spacelight_Models_NightlightRealmProxy.createDetachedCopy((com.appsforkids.pasz.spacelight.Models.Nightlight) realmObject, 0, maxDepth, cache));
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public <E extends RealmModel> boolean isEmbedded(Class<E> clazz) {
        if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.MySettings.class)) {
            return false;
        }
        if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.LottieImage.class)) {
            return false;
        }
        if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.ImageFile.class)) {
            return false;
        }
        if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.AudioFile.class)) {
            return false;
        }
        if (clazz.equals(com.appsforkids.pasz.spacelight.Models.Nightlight.class)) {
            return false;
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public <E extends RealmModel> void updateEmbeddedObject(Realm realm, E unmanagedObject, E managedObject, Map<RealmModel, RealmObjectProxy> cache, Set<ImportFlag> flags) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<E> clazz = (Class<E>) managedObject.getClass().getSuperclass();

        if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.MySettings.class)) {
            throw getNotEmbeddedClassException("com.appsforkids.pasz.spacelight.RealmObjects.MySettings");
        } else if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.LottieImage.class)) {
            throw getNotEmbeddedClassException("com.appsforkids.pasz.spacelight.RealmObjects.LottieImage");
        } else if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.ImageFile.class)) {
            throw getNotEmbeddedClassException("com.appsforkids.pasz.spacelight.RealmObjects.ImageFile");
        } else if (clazz.equals(com.appsforkids.pasz.spacelight.RealmObjects.AudioFile.class)) {
            throw getNotEmbeddedClassException("com.appsforkids.pasz.spacelight.RealmObjects.AudioFile");
        } else if (clazz.equals(com.appsforkids.pasz.spacelight.Models.Nightlight.class)) {
            throw getNotEmbeddedClassException("com.appsforkids.pasz.spacelight.Models.Nightlight");
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

}
