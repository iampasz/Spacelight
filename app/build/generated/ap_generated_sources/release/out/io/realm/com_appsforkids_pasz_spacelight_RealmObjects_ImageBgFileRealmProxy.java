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
public class com_appsforkids_pasz_spacelight_RealmObjects_ImageBgFileRealmProxy extends com.appsforkids.pasz.spacelight.RealmObjects.ImageBgFile
    implements RealmObjectProxy, com_appsforkids_pasz_spacelight_RealmObjects_ImageBgFileRealmProxyInterface {

    static final class ImageBgFileColumnInfo extends ColumnInfo {
        long imageInternetLinkColKey;

        ImageBgFileColumnInfo(OsSchemaInfo schemaInfo) {
            super(1);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("ImageBgFile");
            this.imageInternetLinkColKey = addColumnDetails("imageInternetLink", "imageInternetLink", objectSchemaInfo);
        }

        ImageBgFileColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new ImageBgFileColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final ImageBgFileColumnInfo src = (ImageBgFileColumnInfo) rawSrc;
            final ImageBgFileColumnInfo dst = (ImageBgFileColumnInfo) rawDst;
            dst.imageInternetLinkColKey = src.imageInternetLinkColKey;
        }
    }

    private static final String NO_ALIAS = "";
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();

    private ImageBgFileColumnInfo columnInfo;
    private ProxyState<com.appsforkids.pasz.spacelight.RealmObjects.ImageBgFile> proxyState;

    com_appsforkids_pasz_spacelight_RealmObjects_ImageBgFileRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (ImageBgFileColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.appsforkids.pasz.spacelight.RealmObjects.ImageBgFile>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$imageInternetLink() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.imageInternetLinkColKey);
    }

    @Override
    public void realmSet$imageInternetLink(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.imageInternetLinkColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.imageInternetLinkColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.imageInternetLinkColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.imageInternetLinkColKey, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(NO_ALIAS, "ImageBgFile", false, 1, 0);
        builder.addPersistedProperty(NO_ALIAS, "imageInternetLink", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static ImageBgFileColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new ImageBgFileColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "ImageBgFile";
    }

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "ImageBgFile";
    }

    @SuppressWarnings("cast")
    public static com.appsforkids.pasz.spacelight.RealmObjects.ImageBgFile createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.appsforkids.pasz.spacelight.RealmObjects.ImageBgFile obj = realm.createObjectInternal(com.appsforkids.pasz.spacelight.RealmObjects.ImageBgFile.class, true, excludeFields);

        final com_appsforkids_pasz_spacelight_RealmObjects_ImageBgFileRealmProxyInterface objProxy = (com_appsforkids_pasz_spacelight_RealmObjects_ImageBgFileRealmProxyInterface) obj;
        if (json.has("imageInternetLink")) {
            if (json.isNull("imageInternetLink")) {
                objProxy.realmSet$imageInternetLink(null);
            } else {
                objProxy.realmSet$imageInternetLink((String) json.getString("imageInternetLink"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.appsforkids.pasz.spacelight.RealmObjects.ImageBgFile createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        final com.appsforkids.pasz.spacelight.RealmObjects.ImageBgFile obj = new com.appsforkids.pasz.spacelight.RealmObjects.ImageBgFile();
        final com_appsforkids_pasz_spacelight_RealmObjects_ImageBgFileRealmProxyInterface objProxy = (com_appsforkids_pasz_spacelight_RealmObjects_ImageBgFileRealmProxyInterface) obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("imageInternetLink")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$imageInternetLink((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$imageInternetLink(null);
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return realm.copyToRealm(obj);
    }

    static com_appsforkids_pasz_spacelight_RealmObjects_ImageBgFileRealmProxy newProxyInstance(BaseRealm realm, Row row) {
        // Ignore default values to avoid creating unexpected objects from RealmModel/RealmList fields
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        objectContext.set(realm, row, realm.getSchema().getColumnInfo(com.appsforkids.pasz.spacelight.RealmObjects.ImageBgFile.class), false, Collections.<String>emptyList());
        io.realm.com_appsforkids_pasz_spacelight_RealmObjects_ImageBgFileRealmProxy obj = new io.realm.com_appsforkids_pasz_spacelight_RealmObjects_ImageBgFileRealmProxy();
        objectContext.clear();
        return obj;
    }

    public static com.appsforkids.pasz.spacelight.RealmObjects.ImageBgFile copyOrUpdate(Realm realm, ImageBgFileColumnInfo columnInfo, com.appsforkids.pasz.spacelight.RealmObjects.ImageBgFile object, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
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
            return (com.appsforkids.pasz.spacelight.RealmObjects.ImageBgFile) cachedRealmObject;
        }

        return copy(realm, columnInfo, object, update, cache, flags);
    }

    public static com.appsforkids.pasz.spacelight.RealmObjects.ImageBgFile copy(Realm realm, ImageBgFileColumnInfo columnInfo, com.appsforkids.pasz.spacelight.RealmObjects.ImageBgFile newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.appsforkids.pasz.spacelight.RealmObjects.ImageBgFile) cachedRealmObject;
        }

        com_appsforkids_pasz_spacelight_RealmObjects_ImageBgFileRealmProxyInterface unmanagedSource = (com_appsforkids_pasz_spacelight_RealmObjects_ImageBgFileRealmProxyInterface) newObject;

        Table table = realm.getTable(com.appsforkids.pasz.spacelight.RealmObjects.ImageBgFile.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, flags);

        // Add all non-"object reference" fields
        builder.addString(columnInfo.imageInternetLinkColKey, unmanagedSource.realmGet$imageInternetLink());

        // Create the underlying object and cache it before setting any object/objectlist references
        // This will allow us to break any circular dependencies by using the object cache.
        Row row = builder.createNewObject();
        io.realm.com_appsforkids_pasz_spacelight_RealmObjects_ImageBgFileRealmProxy managedCopy = newProxyInstance(realm, row);
        cache.put(newObject, managedCopy);

        return managedCopy;
    }

    public static long insert(Realm realm, com.appsforkids.pasz.spacelight.RealmObjects.ImageBgFile object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey();
        }
        Table table = realm.getTable(com.appsforkids.pasz.spacelight.RealmObjects.ImageBgFile.class);
        long tableNativePtr = table.getNativePtr();
        ImageBgFileColumnInfo columnInfo = (ImageBgFileColumnInfo) realm.getSchema().getColumnInfo(com.appsforkids.pasz.spacelight.RealmObjects.ImageBgFile.class);
        long objKey = OsObject.createRow(table);
        cache.put(object, objKey);
        String realmGet$imageInternetLink = ((com_appsforkids_pasz_spacelight_RealmObjects_ImageBgFileRealmProxyInterface) object).realmGet$imageInternetLink();
        if (realmGet$imageInternetLink != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.imageInternetLinkColKey, objKey, realmGet$imageInternetLink, false);
        }
        return objKey;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.appsforkids.pasz.spacelight.RealmObjects.ImageBgFile.class);
        long tableNativePtr = table.getNativePtr();
        ImageBgFileColumnInfo columnInfo = (ImageBgFileColumnInfo) realm.getSchema().getColumnInfo(com.appsforkids.pasz.spacelight.RealmObjects.ImageBgFile.class);
        com.appsforkids.pasz.spacelight.RealmObjects.ImageBgFile object = null;
        while (objects.hasNext()) {
            object = (com.appsforkids.pasz.spacelight.RealmObjects.ImageBgFile) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey());
                continue;
            }
            long objKey = OsObject.createRow(table);
            cache.put(object, objKey);
            String realmGet$imageInternetLink = ((com_appsforkids_pasz_spacelight_RealmObjects_ImageBgFileRealmProxyInterface) object).realmGet$imageInternetLink();
            if (realmGet$imageInternetLink != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.imageInternetLinkColKey, objKey, realmGet$imageInternetLink, false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.appsforkids.pasz.spacelight.RealmObjects.ImageBgFile object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey();
        }
        Table table = realm.getTable(com.appsforkids.pasz.spacelight.RealmObjects.ImageBgFile.class);
        long tableNativePtr = table.getNativePtr();
        ImageBgFileColumnInfo columnInfo = (ImageBgFileColumnInfo) realm.getSchema().getColumnInfo(com.appsforkids.pasz.spacelight.RealmObjects.ImageBgFile.class);
        long objKey = OsObject.createRow(table);
        cache.put(object, objKey);
        String realmGet$imageInternetLink = ((com_appsforkids_pasz_spacelight_RealmObjects_ImageBgFileRealmProxyInterface) object).realmGet$imageInternetLink();
        if (realmGet$imageInternetLink != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.imageInternetLinkColKey, objKey, realmGet$imageInternetLink, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.imageInternetLinkColKey, objKey, false);
        }
        return objKey;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.appsforkids.pasz.spacelight.RealmObjects.ImageBgFile.class);
        long tableNativePtr = table.getNativePtr();
        ImageBgFileColumnInfo columnInfo = (ImageBgFileColumnInfo) realm.getSchema().getColumnInfo(com.appsforkids.pasz.spacelight.RealmObjects.ImageBgFile.class);
        com.appsforkids.pasz.spacelight.RealmObjects.ImageBgFile object = null;
        while (objects.hasNext()) {
            object = (com.appsforkids.pasz.spacelight.RealmObjects.ImageBgFile) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey());
                continue;
            }
            long objKey = OsObject.createRow(table);
            cache.put(object, objKey);
            String realmGet$imageInternetLink = ((com_appsforkids_pasz_spacelight_RealmObjects_ImageBgFileRealmProxyInterface) object).realmGet$imageInternetLink();
            if (realmGet$imageInternetLink != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.imageInternetLinkColKey, objKey, realmGet$imageInternetLink, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.imageInternetLinkColKey, objKey, false);
            }
        }
    }

    public static com.appsforkids.pasz.spacelight.RealmObjects.ImageBgFile createDetachedCopy(com.appsforkids.pasz.spacelight.RealmObjects.ImageBgFile realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.appsforkids.pasz.spacelight.RealmObjects.ImageBgFile unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.appsforkids.pasz.spacelight.RealmObjects.ImageBgFile();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.appsforkids.pasz.spacelight.RealmObjects.ImageBgFile) cachedObject.object;
            }
            unmanagedObject = (com.appsforkids.pasz.spacelight.RealmObjects.ImageBgFile) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        com_appsforkids_pasz_spacelight_RealmObjects_ImageBgFileRealmProxyInterface unmanagedCopy = (com_appsforkids_pasz_spacelight_RealmObjects_ImageBgFileRealmProxyInterface) unmanagedObject;
        com_appsforkids_pasz_spacelight_RealmObjects_ImageBgFileRealmProxyInterface realmSource = (com_appsforkids_pasz_spacelight_RealmObjects_ImageBgFileRealmProxyInterface) realmObject;
        Realm objectRealm = (Realm) ((RealmObjectProxy) realmObject).realmGet$proxyState().getRealm$realm();
        unmanagedCopy.realmSet$imageInternetLink(realmSource.realmGet$imageInternetLink());

        return unmanagedObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("ImageBgFile = proxy[");
        stringBuilder.append("{imageInternetLink:");
        stringBuilder.append(realmGet$imageInternetLink() != null ? realmGet$imageInternetLink() : "null");
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
        com_appsforkids_pasz_spacelight_RealmObjects_ImageBgFileRealmProxy aImageBgFile = (com_appsforkids_pasz_spacelight_RealmObjects_ImageBgFileRealmProxy)o;

        BaseRealm realm = proxyState.getRealm$realm();
        BaseRealm otherRealm = aImageBgFile.proxyState.getRealm$realm();
        String path = realm.getPath();
        String otherPath = otherRealm.getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;
        if (realm.isFrozen() != otherRealm.isFrozen()) return false;
        if (!realm.sharedRealm.getVersionID().equals(otherRealm.sharedRealm.getVersionID())) {
            return false;
        }

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aImageBgFile.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getObjectKey() != aImageBgFile.proxyState.getRow$realm().getObjectKey()) return false;

        return true;
    }
}
