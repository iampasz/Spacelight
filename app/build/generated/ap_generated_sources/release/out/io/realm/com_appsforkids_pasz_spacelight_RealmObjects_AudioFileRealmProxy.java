package io.realm;


import android.annotation.TargetApi;
import android.os.Build;
import android.util.JsonReader;
import android.util.JsonToken;
import io.realm.ImportFlag;
import io.realm.ProxyUtils;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.NativeContext;
import io.realm.internal.OsList;
import io.realm.internal.OsMap;
import io.realm.internal.OsObject;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.OsSet;
import io.realm.internal.Property;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import io.realm.internal.Table;
import io.realm.internal.android.JsonUtils;
import io.realm.internal.core.NativeRealmAny;
import io.realm.internal.objectstore.OsObjectBuilder;
import io.realm.log.RealmLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("all")
public class com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxy extends com.appsforkids.pasz.spacelight.RealmObjects.AudioFile
    implements RealmObjectProxy, com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxyInterface {

    static final class AudioFileColumnInfo extends ColumnInfo {
        long idColKey;
        long nameSongColKey;
        long authorSongColKey;
        long statusColKey;
        long resourseLinkColKey;
        long internetLinkColKey;
        long lockalLinkColKey;
        long fileNameColKey;
        long isPlayColKey;

        AudioFileColumnInfo(OsSchemaInfo schemaInfo) {
            super(9);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("AudioFile");
            this.idColKey = addColumnDetails("id", "id", objectSchemaInfo);
            this.nameSongColKey = addColumnDetails("nameSong", "nameSong", objectSchemaInfo);
            this.authorSongColKey = addColumnDetails("authorSong", "authorSong", objectSchemaInfo);
            this.statusColKey = addColumnDetails("status", "status", objectSchemaInfo);
            this.resourseLinkColKey = addColumnDetails("resourseLink", "resourseLink", objectSchemaInfo);
            this.internetLinkColKey = addColumnDetails("internetLink", "internetLink", objectSchemaInfo);
            this.lockalLinkColKey = addColumnDetails("lockalLink", "lockalLink", objectSchemaInfo);
            this.fileNameColKey = addColumnDetails("fileName", "fileName", objectSchemaInfo);
            this.isPlayColKey = addColumnDetails("isPlay", "isPlay", objectSchemaInfo);
        }

        AudioFileColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new AudioFileColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final AudioFileColumnInfo src = (AudioFileColumnInfo) rawSrc;
            final AudioFileColumnInfo dst = (AudioFileColumnInfo) rawDst;
            dst.idColKey = src.idColKey;
            dst.nameSongColKey = src.nameSongColKey;
            dst.authorSongColKey = src.authorSongColKey;
            dst.statusColKey = src.statusColKey;
            dst.resourseLinkColKey = src.resourseLinkColKey;
            dst.internetLinkColKey = src.internetLinkColKey;
            dst.lockalLinkColKey = src.lockalLinkColKey;
            dst.fileNameColKey = src.fileNameColKey;
            dst.isPlayColKey = src.isPlayColKey;
        }
    }

    private static final String NO_ALIAS = "";
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();

    private AudioFileColumnInfo columnInfo;
    private ProxyState<com.appsforkids.pasz.spacelight.RealmObjects.AudioFile> proxyState;

    com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (AudioFileColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.appsforkids.pasz.spacelight.RealmObjects.AudioFile>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$id() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.idColKey);
    }

    @Override
    public void realmSet$id(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.idColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.idColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$nameSong() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.nameSongColKey);
    }

    @Override
    public void realmSet$nameSong(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.nameSongColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.nameSongColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.nameSongColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.nameSongColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$authorSong() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.authorSongColKey);
    }

    @Override
    public void realmSet$authorSong(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.authorSongColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.authorSongColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.authorSongColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.authorSongColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public Boolean realmGet$status() {
        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNull(columnInfo.statusColKey)) {
            return null;
        }
        return (boolean) proxyState.getRow$realm().getBoolean(columnInfo.statusColKey);
    }

    @Override
    public void realmSet$status(Boolean value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.statusColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setBoolean(columnInfo.statusColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.statusColKey);
            return;
        }
        proxyState.getRow$realm().setBoolean(columnInfo.statusColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$resourseLink() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.resourseLinkColKey);
    }

    @Override
    public void realmSet$resourseLink(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.resourseLinkColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.resourseLinkColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$internetLink() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.internetLinkColKey);
    }

    @Override
    public void realmSet$internetLink(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.internetLinkColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.internetLinkColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.internetLinkColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.internetLinkColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$lockalLink() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.lockalLinkColKey);
    }

    @Override
    public void realmSet$lockalLink(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.lockalLinkColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.lockalLinkColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.lockalLinkColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.lockalLinkColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$fileName() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.fileNameColKey);
    }

    @Override
    public void realmSet$fileName(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.fileNameColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.fileNameColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.fileNameColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.fileNameColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public Boolean realmGet$isPlay() {
        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNull(columnInfo.isPlayColKey)) {
            return null;
        }
        return (boolean) proxyState.getRow$realm().getBoolean(columnInfo.isPlayColKey);
    }

    @Override
    public void realmSet$isPlay(Boolean value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.isPlayColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setBoolean(columnInfo.isPlayColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.isPlayColKey);
            return;
        }
        proxyState.getRow$realm().setBoolean(columnInfo.isPlayColKey, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(NO_ALIAS, "AudioFile", false, 9, 0);
        builder.addPersistedProperty(NO_ALIAS, "id", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "nameSong", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "authorSong", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "status", RealmFieldType.BOOLEAN, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "resourseLink", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "internetLink", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "lockalLink", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "fileName", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "isPlay", RealmFieldType.BOOLEAN, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static AudioFileColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new AudioFileColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "AudioFile";
    }

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "AudioFile";
    }

    @SuppressWarnings("cast")
    public static com.appsforkids.pasz.spacelight.RealmObjects.AudioFile createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.appsforkids.pasz.spacelight.RealmObjects.AudioFile obj = realm.createObjectInternal(com.appsforkids.pasz.spacelight.RealmObjects.AudioFile.class, true, excludeFields);

        final com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxyInterface objProxy = (com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxyInterface) obj;
        if (json.has("id")) {
            if (json.isNull("id")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'id' to null.");
            } else {
                objProxy.realmSet$id((int) json.getInt("id"));
            }
        }
        if (json.has("nameSong")) {
            if (json.isNull("nameSong")) {
                objProxy.realmSet$nameSong(null);
            } else {
                objProxy.realmSet$nameSong((String) json.getString("nameSong"));
            }
        }
        if (json.has("authorSong")) {
            if (json.isNull("authorSong")) {
                objProxy.realmSet$authorSong(null);
            } else {
                objProxy.realmSet$authorSong((String) json.getString("authorSong"));
            }
        }
        if (json.has("status")) {
            if (json.isNull("status")) {
                objProxy.realmSet$status(null);
            } else {
                objProxy.realmSet$status((boolean) json.getBoolean("status"));
            }
        }
        if (json.has("resourseLink")) {
            if (json.isNull("resourseLink")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'resourseLink' to null.");
            } else {
                objProxy.realmSet$resourseLink((int) json.getInt("resourseLink"));
            }
        }
        if (json.has("internetLink")) {
            if (json.isNull("internetLink")) {
                objProxy.realmSet$internetLink(null);
            } else {
                objProxy.realmSet$internetLink((String) json.getString("internetLink"));
            }
        }
        if (json.has("lockalLink")) {
            if (json.isNull("lockalLink")) {
                objProxy.realmSet$lockalLink(null);
            } else {
                objProxy.realmSet$lockalLink((String) json.getString("lockalLink"));
            }
        }
        if (json.has("fileName")) {
            if (json.isNull("fileName")) {
                objProxy.realmSet$fileName(null);
            } else {
                objProxy.realmSet$fileName((String) json.getString("fileName"));
            }
        }
        if (json.has("isPlay")) {
            if (json.isNull("isPlay")) {
                objProxy.realmSet$isPlay(null);
            } else {
                objProxy.realmSet$isPlay((boolean) json.getBoolean("isPlay"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.appsforkids.pasz.spacelight.RealmObjects.AudioFile createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        final com.appsforkids.pasz.spacelight.RealmObjects.AudioFile obj = new com.appsforkids.pasz.spacelight.RealmObjects.AudioFile();
        final com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxyInterface objProxy = (com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxyInterface) obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("id")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$id((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'id' to null.");
                }
            } else if (name.equals("nameSong")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$nameSong((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$nameSong(null);
                }
            } else if (name.equals("authorSong")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$authorSong((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$authorSong(null);
                }
            } else if (name.equals("status")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$status((boolean) reader.nextBoolean());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$status(null);
                }
            } else if (name.equals("resourseLink")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$resourseLink((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'resourseLink' to null.");
                }
            } else if (name.equals("internetLink")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$internetLink((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$internetLink(null);
                }
            } else if (name.equals("lockalLink")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$lockalLink((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$lockalLink(null);
                }
            } else if (name.equals("fileName")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$fileName((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$fileName(null);
                }
            } else if (name.equals("isPlay")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$isPlay((boolean) reader.nextBoolean());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$isPlay(null);
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return realm.copyToRealm(obj);
    }

    static com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxy newProxyInstance(BaseRealm realm, Row row) {
        // Ignore default values to avoid creating unexpected objects from RealmModel/RealmList fields
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        objectContext.set(realm, row, realm.getSchema().getColumnInfo(com.appsforkids.pasz.spacelight.RealmObjects.AudioFile.class), false, Collections.<String>emptyList());
        io.realm.com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxy obj = new io.realm.com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxy();
        objectContext.clear();
        return obj;
    }

    public static com.appsforkids.pasz.spacelight.RealmObjects.AudioFile copyOrUpdate(Realm realm, AudioFileColumnInfo columnInfo, com.appsforkids.pasz.spacelight.RealmObjects.AudioFile object, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null) {
            final BaseRealm otherRealm = ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm();
            if (otherRealm.threadId != realm.threadId) {
                throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
            }
            if (otherRealm.getPath().equals(realm.getPath())) {
                return object;
            }
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.appsforkids.pasz.spacelight.RealmObjects.AudioFile) cachedRealmObject;
        }

        return copy(realm, columnInfo, object, update, cache, flags);
    }

    public static com.appsforkids.pasz.spacelight.RealmObjects.AudioFile copy(Realm realm, AudioFileColumnInfo columnInfo, com.appsforkids.pasz.spacelight.RealmObjects.AudioFile newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.appsforkids.pasz.spacelight.RealmObjects.AudioFile) cachedRealmObject;
        }

        com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxyInterface unmanagedSource = (com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxyInterface) newObject;

        Table table = realm.getTable(com.appsforkids.pasz.spacelight.RealmObjects.AudioFile.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, flags);

        // Add all non-"object reference" fields
        builder.addInteger(columnInfo.idColKey, unmanagedSource.realmGet$id());
        builder.addString(columnInfo.nameSongColKey, unmanagedSource.realmGet$nameSong());
        builder.addString(columnInfo.authorSongColKey, unmanagedSource.realmGet$authorSong());
        builder.addBoolean(columnInfo.statusColKey, unmanagedSource.realmGet$status());
        builder.addInteger(columnInfo.resourseLinkColKey, unmanagedSource.realmGet$resourseLink());
        builder.addString(columnInfo.internetLinkColKey, unmanagedSource.realmGet$internetLink());
        builder.addString(columnInfo.lockalLinkColKey, unmanagedSource.realmGet$lockalLink());
        builder.addString(columnInfo.fileNameColKey, unmanagedSource.realmGet$fileName());
        builder.addBoolean(columnInfo.isPlayColKey, unmanagedSource.realmGet$isPlay());

        // Create the underlying object and cache it before setting any object/objectlist references
        // This will allow us to break any circular dependencies by using the object cache.
        Row row = builder.createNewObject();
        io.realm.com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxy managedCopy = newProxyInstance(realm, row);
        cache.put(newObject, managedCopy);

        return managedCopy;
    }

    public static long insert(Realm realm, com.appsforkids.pasz.spacelight.RealmObjects.AudioFile object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey();
        }
        Table table = realm.getTable(com.appsforkids.pasz.spacelight.RealmObjects.AudioFile.class);
        long tableNativePtr = table.getNativePtr();
        AudioFileColumnInfo columnInfo = (AudioFileColumnInfo) realm.getSchema().getColumnInfo(com.appsforkids.pasz.spacelight.RealmObjects.AudioFile.class);
        long objKey = OsObject.createRow(table);
        cache.put(object, objKey);
        Table.nativeSetLong(tableNativePtr, columnInfo.idColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxyInterface) object).realmGet$id(), false);
        String realmGet$nameSong = ((com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxyInterface) object).realmGet$nameSong();
        if (realmGet$nameSong != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameSongColKey, objKey, realmGet$nameSong, false);
        }
        String realmGet$authorSong = ((com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxyInterface) object).realmGet$authorSong();
        if (realmGet$authorSong != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.authorSongColKey, objKey, realmGet$authorSong, false);
        }
        Boolean realmGet$status = ((com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxyInterface) object).realmGet$status();
        if (realmGet$status != null) {
            Table.nativeSetBoolean(tableNativePtr, columnInfo.statusColKey, objKey, realmGet$status, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.resourseLinkColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxyInterface) object).realmGet$resourseLink(), false);
        String realmGet$internetLink = ((com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxyInterface) object).realmGet$internetLink();
        if (realmGet$internetLink != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.internetLinkColKey, objKey, realmGet$internetLink, false);
        }
        String realmGet$lockalLink = ((com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxyInterface) object).realmGet$lockalLink();
        if (realmGet$lockalLink != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.lockalLinkColKey, objKey, realmGet$lockalLink, false);
        }
        String realmGet$fileName = ((com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxyInterface) object).realmGet$fileName();
        if (realmGet$fileName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.fileNameColKey, objKey, realmGet$fileName, false);
        }
        Boolean realmGet$isPlay = ((com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxyInterface) object).realmGet$isPlay();
        if (realmGet$isPlay != null) {
            Table.nativeSetBoolean(tableNativePtr, columnInfo.isPlayColKey, objKey, realmGet$isPlay, false);
        }
        return objKey;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.appsforkids.pasz.spacelight.RealmObjects.AudioFile.class);
        long tableNativePtr = table.getNativePtr();
        AudioFileColumnInfo columnInfo = (AudioFileColumnInfo) realm.getSchema().getColumnInfo(com.appsforkids.pasz.spacelight.RealmObjects.AudioFile.class);
        com.appsforkids.pasz.spacelight.RealmObjects.AudioFile object = null;
        while (objects.hasNext()) {
            object = (com.appsforkids.pasz.spacelight.RealmObjects.AudioFile) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey());
                continue;
            }
            long objKey = OsObject.createRow(table);
            cache.put(object, objKey);
            Table.nativeSetLong(tableNativePtr, columnInfo.idColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxyInterface) object).realmGet$id(), false);
            String realmGet$nameSong = ((com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxyInterface) object).realmGet$nameSong();
            if (realmGet$nameSong != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nameSongColKey, objKey, realmGet$nameSong, false);
            }
            String realmGet$authorSong = ((com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxyInterface) object).realmGet$authorSong();
            if (realmGet$authorSong != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.authorSongColKey, objKey, realmGet$authorSong, false);
            }
            Boolean realmGet$status = ((com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxyInterface) object).realmGet$status();
            if (realmGet$status != null) {
                Table.nativeSetBoolean(tableNativePtr, columnInfo.statusColKey, objKey, realmGet$status, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.resourseLinkColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxyInterface) object).realmGet$resourseLink(), false);
            String realmGet$internetLink = ((com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxyInterface) object).realmGet$internetLink();
            if (realmGet$internetLink != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.internetLinkColKey, objKey, realmGet$internetLink, false);
            }
            String realmGet$lockalLink = ((com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxyInterface) object).realmGet$lockalLink();
            if (realmGet$lockalLink != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.lockalLinkColKey, objKey, realmGet$lockalLink, false);
            }
            String realmGet$fileName = ((com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxyInterface) object).realmGet$fileName();
            if (realmGet$fileName != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.fileNameColKey, objKey, realmGet$fileName, false);
            }
            Boolean realmGet$isPlay = ((com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxyInterface) object).realmGet$isPlay();
            if (realmGet$isPlay != null) {
                Table.nativeSetBoolean(tableNativePtr, columnInfo.isPlayColKey, objKey, realmGet$isPlay, false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.appsforkids.pasz.spacelight.RealmObjects.AudioFile object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey();
        }
        Table table = realm.getTable(com.appsforkids.pasz.spacelight.RealmObjects.AudioFile.class);
        long tableNativePtr = table.getNativePtr();
        AudioFileColumnInfo columnInfo = (AudioFileColumnInfo) realm.getSchema().getColumnInfo(com.appsforkids.pasz.spacelight.RealmObjects.AudioFile.class);
        long objKey = OsObject.createRow(table);
        cache.put(object, objKey);
        Table.nativeSetLong(tableNativePtr, columnInfo.idColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxyInterface) object).realmGet$id(), false);
        String realmGet$nameSong = ((com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxyInterface) object).realmGet$nameSong();
        if (realmGet$nameSong != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameSongColKey, objKey, realmGet$nameSong, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nameSongColKey, objKey, false);
        }
        String realmGet$authorSong = ((com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxyInterface) object).realmGet$authorSong();
        if (realmGet$authorSong != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.authorSongColKey, objKey, realmGet$authorSong, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.authorSongColKey, objKey, false);
        }
        Boolean realmGet$status = ((com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxyInterface) object).realmGet$status();
        if (realmGet$status != null) {
            Table.nativeSetBoolean(tableNativePtr, columnInfo.statusColKey, objKey, realmGet$status, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.statusColKey, objKey, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.resourseLinkColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxyInterface) object).realmGet$resourseLink(), false);
        String realmGet$internetLink = ((com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxyInterface) object).realmGet$internetLink();
        if (realmGet$internetLink != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.internetLinkColKey, objKey, realmGet$internetLink, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.internetLinkColKey, objKey, false);
        }
        String realmGet$lockalLink = ((com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxyInterface) object).realmGet$lockalLink();
        if (realmGet$lockalLink != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.lockalLinkColKey, objKey, realmGet$lockalLink, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.lockalLinkColKey, objKey, false);
        }
        String realmGet$fileName = ((com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxyInterface) object).realmGet$fileName();
        if (realmGet$fileName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.fileNameColKey, objKey, realmGet$fileName, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.fileNameColKey, objKey, false);
        }
        Boolean realmGet$isPlay = ((com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxyInterface) object).realmGet$isPlay();
        if (realmGet$isPlay != null) {
            Table.nativeSetBoolean(tableNativePtr, columnInfo.isPlayColKey, objKey, realmGet$isPlay, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.isPlayColKey, objKey, false);
        }
        return objKey;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.appsforkids.pasz.spacelight.RealmObjects.AudioFile.class);
        long tableNativePtr = table.getNativePtr();
        AudioFileColumnInfo columnInfo = (AudioFileColumnInfo) realm.getSchema().getColumnInfo(com.appsforkids.pasz.spacelight.RealmObjects.AudioFile.class);
        com.appsforkids.pasz.spacelight.RealmObjects.AudioFile object = null;
        while (objects.hasNext()) {
            object = (com.appsforkids.pasz.spacelight.RealmObjects.AudioFile) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey());
                continue;
            }
            long objKey = OsObject.createRow(table);
            cache.put(object, objKey);
            Table.nativeSetLong(tableNativePtr, columnInfo.idColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxyInterface) object).realmGet$id(), false);
            String realmGet$nameSong = ((com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxyInterface) object).realmGet$nameSong();
            if (realmGet$nameSong != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nameSongColKey, objKey, realmGet$nameSong, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.nameSongColKey, objKey, false);
            }
            String realmGet$authorSong = ((com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxyInterface) object).realmGet$authorSong();
            if (realmGet$authorSong != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.authorSongColKey, objKey, realmGet$authorSong, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.authorSongColKey, objKey, false);
            }
            Boolean realmGet$status = ((com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxyInterface) object).realmGet$status();
            if (realmGet$status != null) {
                Table.nativeSetBoolean(tableNativePtr, columnInfo.statusColKey, objKey, realmGet$status, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.statusColKey, objKey, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.resourseLinkColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxyInterface) object).realmGet$resourseLink(), false);
            String realmGet$internetLink = ((com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxyInterface) object).realmGet$internetLink();
            if (realmGet$internetLink != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.internetLinkColKey, objKey, realmGet$internetLink, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.internetLinkColKey, objKey, false);
            }
            String realmGet$lockalLink = ((com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxyInterface) object).realmGet$lockalLink();
            if (realmGet$lockalLink != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.lockalLinkColKey, objKey, realmGet$lockalLink, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.lockalLinkColKey, objKey, false);
            }
            String realmGet$fileName = ((com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxyInterface) object).realmGet$fileName();
            if (realmGet$fileName != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.fileNameColKey, objKey, realmGet$fileName, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.fileNameColKey, objKey, false);
            }
            Boolean realmGet$isPlay = ((com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxyInterface) object).realmGet$isPlay();
            if (realmGet$isPlay != null) {
                Table.nativeSetBoolean(tableNativePtr, columnInfo.isPlayColKey, objKey, realmGet$isPlay, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.isPlayColKey, objKey, false);
            }
        }
    }

    public static com.appsforkids.pasz.spacelight.RealmObjects.AudioFile createDetachedCopy(com.appsforkids.pasz.spacelight.RealmObjects.AudioFile realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.appsforkids.pasz.spacelight.RealmObjects.AudioFile unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.appsforkids.pasz.spacelight.RealmObjects.AudioFile();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.appsforkids.pasz.spacelight.RealmObjects.AudioFile) cachedObject.object;
            }
            unmanagedObject = (com.appsforkids.pasz.spacelight.RealmObjects.AudioFile) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxyInterface unmanagedCopy = (com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxyInterface) unmanagedObject;
        com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxyInterface realmSource = (com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxyInterface) realmObject;
        Realm objectRealm = (Realm) ((RealmObjectProxy) realmObject).realmGet$proxyState().getRealm$realm();
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$nameSong(realmSource.realmGet$nameSong());
        unmanagedCopy.realmSet$authorSong(realmSource.realmGet$authorSong());
        unmanagedCopy.realmSet$status(realmSource.realmGet$status());
        unmanagedCopy.realmSet$resourseLink(realmSource.realmGet$resourseLink());
        unmanagedCopy.realmSet$internetLink(realmSource.realmGet$internetLink());
        unmanagedCopy.realmSet$lockalLink(realmSource.realmGet$lockalLink());
        unmanagedCopy.realmSet$fileName(realmSource.realmGet$fileName());
        unmanagedCopy.realmSet$isPlay(realmSource.realmGet$isPlay());

        return unmanagedObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("AudioFile = proxy[");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{nameSong:");
        stringBuilder.append(realmGet$nameSong() != null ? realmGet$nameSong() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{authorSong:");
        stringBuilder.append(realmGet$authorSong() != null ? realmGet$authorSong() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{status:");
        stringBuilder.append(realmGet$status() != null ? realmGet$status() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{resourseLink:");
        stringBuilder.append(realmGet$resourseLink());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{internetLink:");
        stringBuilder.append(realmGet$internetLink() != null ? realmGet$internetLink() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{lockalLink:");
        stringBuilder.append(realmGet$lockalLink() != null ? realmGet$lockalLink() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{fileName:");
        stringBuilder.append(realmGet$fileName() != null ? realmGet$fileName() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{isPlay:");
        stringBuilder.append(realmGet$isPlay() != null ? realmGet$isPlay() : "null");
        stringBuilder.append("}");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public ProxyState<?> realmGet$proxyState() {
        return proxyState;
    }

    @Override
    public int hashCode() {
        String realmName = proxyState.getRealm$realm().getPath();
        String tableName = proxyState.getRow$realm().getTable().getName();
        long objKey = proxyState.getRow$realm().getObjectKey();

        int result = 17;
        result = 31 * result + ((realmName != null) ? realmName.hashCode() : 0);
        result = 31 * result + ((tableName != null) ? tableName.hashCode() : 0);
        result = 31 * result + (int) (objKey ^ (objKey >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxy aAudioFile = (com_appsforkids_pasz_spacelight_RealmObjects_AudioFileRealmProxy)o;

        BaseRealm realm = proxyState.getRealm$realm();
        BaseRealm otherRealm = aAudioFile.proxyState.getRealm$realm();
        String path = realm.getPath();
        String otherPath = otherRealm.getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;
        if (realm.isFrozen() != otherRealm.isFrozen()) return false;
        if (!realm.sharedRealm.getVersionID().equals(otherRealm.sharedRealm.getVersionID())) {
            return false;
        }

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aAudioFile.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getObjectKey() != aAudioFile.proxyState.getRow$realm().getObjectKey()) return false;

        return true;
    }
}
