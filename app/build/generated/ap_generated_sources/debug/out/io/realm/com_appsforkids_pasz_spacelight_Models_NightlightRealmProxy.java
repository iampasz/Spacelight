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
public class com_appsforkids_pasz_spacelight_Models_NightlightRealmProxy extends com.appsforkids.pasz.spacelight.Models.Nightlight
    implements RealmObjectProxy, com_appsforkids_pasz_spacelight_Models_NightlightRealmProxyInterface {

    static final class NightlightColumnInfo extends ColumnInfo {
        long numberColKey;
        long timerColKey;

        NightlightColumnInfo(OsSchemaInfo schemaInfo) {
            super(2);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Nightlight");
            this.numberColKey = addColumnDetails("number", "number", objectSchemaInfo);
            this.timerColKey = addColumnDetails("timer", "timer", objectSchemaInfo);
        }

        NightlightColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new NightlightColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final NightlightColumnInfo src = (NightlightColumnInfo) rawSrc;
            final NightlightColumnInfo dst = (NightlightColumnInfo) rawDst;
            dst.numberColKey = src.numberColKey;
            dst.timerColKey = src.timerColKey;
        }
    }

    private static final String NO_ALIAS = "";
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();

    private NightlightColumnInfo columnInfo;
    private ProxyState<com.appsforkids.pasz.spacelight.Models.Nightlight> proxyState;

    com_appsforkids_pasz_spacelight_Models_NightlightRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (NightlightColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.appsforkids.pasz.spacelight.Models.Nightlight>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$number() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.numberColKey);
    }

    @Override
    public void realmSet$number(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.numberColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.numberColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$timer() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.timerColKey);
    }

    @Override
    public void realmSet$timer(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.timerColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.timerColKey, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(NO_ALIAS, "Nightlight", false, 2, 0);
        builder.addPersistedProperty(NO_ALIAS, "number", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "timer", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static NightlightColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new NightlightColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "Nightlight";
    }

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "Nightlight";
    }

    @SuppressWarnings("cast")
    public static com.appsforkids.pasz.spacelight.Models.Nightlight createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.appsforkids.pasz.spacelight.Models.Nightlight obj = realm.createObjectInternal(com.appsforkids.pasz.spacelight.Models.Nightlight.class, true, excludeFields);

        final com_appsforkids_pasz_spacelight_Models_NightlightRealmProxyInterface objProxy = (com_appsforkids_pasz_spacelight_Models_NightlightRealmProxyInterface) obj;
        if (json.has("number")) {
            if (json.isNull("number")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'number' to null.");
            } else {
                objProxy.realmSet$number((int) json.getInt("number"));
            }
        }
        if (json.has("timer")) {
            if (json.isNull("timer")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'timer' to null.");
            } else {
                objProxy.realmSet$timer((int) json.getInt("timer"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.appsforkids.pasz.spacelight.Models.Nightlight createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        final com.appsforkids.pasz.spacelight.Models.Nightlight obj = new com.appsforkids.pasz.spacelight.Models.Nightlight();
        final com_appsforkids_pasz_spacelight_Models_NightlightRealmProxyInterface objProxy = (com_appsforkids_pasz_spacelight_Models_NightlightRealmProxyInterface) obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("number")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$number((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'number' to null.");
                }
            } else if (name.equals("timer")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$timer((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'timer' to null.");
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return realm.copyToRealm(obj);
    }

    static com_appsforkids_pasz_spacelight_Models_NightlightRealmProxy newProxyInstance(BaseRealm realm, Row row) {
        // Ignore default values to avoid creating unexpected objects from RealmModel/RealmList fields
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        objectContext.set(realm, row, realm.getSchema().getColumnInfo(com.appsforkids.pasz.spacelight.Models.Nightlight.class), false, Collections.<String>emptyList());
        io.realm.com_appsforkids_pasz_spacelight_Models_NightlightRealmProxy obj = new io.realm.com_appsforkids_pasz_spacelight_Models_NightlightRealmProxy();
        objectContext.clear();
        return obj;
    }

    public static com.appsforkids.pasz.spacelight.Models.Nightlight copyOrUpdate(Realm realm, NightlightColumnInfo columnInfo, com.appsforkids.pasz.spacelight.Models.Nightlight object, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
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
            return (com.appsforkids.pasz.spacelight.Models.Nightlight) cachedRealmObject;
        }

        return copy(realm, columnInfo, object, update, cache, flags);
    }

    public static com.appsforkids.pasz.spacelight.Models.Nightlight copy(Realm realm, NightlightColumnInfo columnInfo, com.appsforkids.pasz.spacelight.Models.Nightlight newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.appsforkids.pasz.spacelight.Models.Nightlight) cachedRealmObject;
        }

        com_appsforkids_pasz_spacelight_Models_NightlightRealmProxyInterface unmanagedSource = (com_appsforkids_pasz_spacelight_Models_NightlightRealmProxyInterface) newObject;

        Table table = realm.getTable(com.appsforkids.pasz.spacelight.Models.Nightlight.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, flags);

        // Add all non-"object reference" fields
        builder.addInteger(columnInfo.numberColKey, unmanagedSource.realmGet$number());
        builder.addInteger(columnInfo.timerColKey, unmanagedSource.realmGet$timer());

        // Create the underlying object and cache it before setting any object/objectlist references
        // This will allow us to break any circular dependencies by using the object cache.
        Row row = builder.createNewObject();
        io.realm.com_appsforkids_pasz_spacelight_Models_NightlightRealmProxy managedCopy = newProxyInstance(realm, row);
        cache.put(newObject, managedCopy);

        return managedCopy;
    }

    public static long insert(Realm realm, com.appsforkids.pasz.spacelight.Models.Nightlight object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey();
        }
        Table table = realm.getTable(com.appsforkids.pasz.spacelight.Models.Nightlight.class);
        long tableNativePtr = table.getNativePtr();
        NightlightColumnInfo columnInfo = (NightlightColumnInfo) realm.getSchema().getColumnInfo(com.appsforkids.pasz.spacelight.Models.Nightlight.class);
        long objKey = OsObject.createRow(table);
        cache.put(object, objKey);
        Table.nativeSetLong(tableNativePtr, columnInfo.numberColKey, objKey, ((com_appsforkids_pasz_spacelight_Models_NightlightRealmProxyInterface) object).realmGet$number(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.timerColKey, objKey, ((com_appsforkids_pasz_spacelight_Models_NightlightRealmProxyInterface) object).realmGet$timer(), false);
        return objKey;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.appsforkids.pasz.spacelight.Models.Nightlight.class);
        long tableNativePtr = table.getNativePtr();
        NightlightColumnInfo columnInfo = (NightlightColumnInfo) realm.getSchema().getColumnInfo(com.appsforkids.pasz.spacelight.Models.Nightlight.class);
        com.appsforkids.pasz.spacelight.Models.Nightlight object = null;
        while (objects.hasNext()) {
            object = (com.appsforkids.pasz.spacelight.Models.Nightlight) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey());
                continue;
            }
            long objKey = OsObject.createRow(table);
            cache.put(object, objKey);
            Table.nativeSetLong(tableNativePtr, columnInfo.numberColKey, objKey, ((com_appsforkids_pasz_spacelight_Models_NightlightRealmProxyInterface) object).realmGet$number(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.timerColKey, objKey, ((com_appsforkids_pasz_spacelight_Models_NightlightRealmProxyInterface) object).realmGet$timer(), false);
        }
    }

    public static long insertOrUpdate(Realm realm, com.appsforkids.pasz.spacelight.Models.Nightlight object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey();
        }
        Table table = realm.getTable(com.appsforkids.pasz.spacelight.Models.Nightlight.class);
        long tableNativePtr = table.getNativePtr();
        NightlightColumnInfo columnInfo = (NightlightColumnInfo) realm.getSchema().getColumnInfo(com.appsforkids.pasz.spacelight.Models.Nightlight.class);
        long objKey = OsObject.createRow(table);
        cache.put(object, objKey);
        Table.nativeSetLong(tableNativePtr, columnInfo.numberColKey, objKey, ((com_appsforkids_pasz_spacelight_Models_NightlightRealmProxyInterface) object).realmGet$number(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.timerColKey, objKey, ((com_appsforkids_pasz_spacelight_Models_NightlightRealmProxyInterface) object).realmGet$timer(), false);
        return objKey;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.appsforkids.pasz.spacelight.Models.Nightlight.class);
        long tableNativePtr = table.getNativePtr();
        NightlightColumnInfo columnInfo = (NightlightColumnInfo) realm.getSchema().getColumnInfo(com.appsforkids.pasz.spacelight.Models.Nightlight.class);
        com.appsforkids.pasz.spacelight.Models.Nightlight object = null;
        while (objects.hasNext()) {
            object = (com.appsforkids.pasz.spacelight.Models.Nightlight) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey());
                continue;
            }
            long objKey = OsObject.createRow(table);
            cache.put(object, objKey);
            Table.nativeSetLong(tableNativePtr, columnInfo.numberColKey, objKey, ((com_appsforkids_pasz_spacelight_Models_NightlightRealmProxyInterface) object).realmGet$number(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.timerColKey, objKey, ((com_appsforkids_pasz_spacelight_Models_NightlightRealmProxyInterface) object).realmGet$timer(), false);
        }
    }

    public static com.appsforkids.pasz.spacelight.Models.Nightlight createDetachedCopy(com.appsforkids.pasz.spacelight.Models.Nightlight realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.appsforkids.pasz.spacelight.Models.Nightlight unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.appsforkids.pasz.spacelight.Models.Nightlight();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.appsforkids.pasz.spacelight.Models.Nightlight) cachedObject.object;
            }
            unmanagedObject = (com.appsforkids.pasz.spacelight.Models.Nightlight) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        com_appsforkids_pasz_spacelight_Models_NightlightRealmProxyInterface unmanagedCopy = (com_appsforkids_pasz_spacelight_Models_NightlightRealmProxyInterface) unmanagedObject;
        com_appsforkids_pasz_spacelight_Models_NightlightRealmProxyInterface realmSource = (com_appsforkids_pasz_spacelight_Models_NightlightRealmProxyInterface) realmObject;
        Realm objectRealm = (Realm) ((RealmObjectProxy) realmObject).realmGet$proxyState().getRealm$realm();
        unmanagedCopy.realmSet$number(realmSource.realmGet$number());
        unmanagedCopy.realmSet$timer(realmSource.realmGet$timer());

        return unmanagedObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Nightlight = proxy[");
        stringBuilder.append("{number:");
        stringBuilder.append(realmGet$number());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{timer:");
        stringBuilder.append(realmGet$timer());
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
        com_appsforkids_pasz_spacelight_Models_NightlightRealmProxy aNightlight = (com_appsforkids_pasz_spacelight_Models_NightlightRealmProxy)o;

        BaseRealm realm = proxyState.getRealm$realm();
        BaseRealm otherRealm = aNightlight.proxyState.getRealm$realm();
        String path = realm.getPath();
        String otherPath = otherRealm.getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;
        if (realm.isFrozen() != otherRealm.isFrozen()) return false;
        if (!realm.sharedRealm.getVersionID().equals(otherRealm.sharedRealm.getVersionID())) {
            return false;
        }

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aNightlight.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getObjectKey() != aNightlight.proxyState.getRow$realm().getObjectKey()) return false;

        return true;
    }
}
