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
public class com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxy extends com.appsforkids.pasz.spacelight.RealmObjects.MySettings
    implements RealmObjectProxy, com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface {

    static final class MySettingsColumnInfo extends ColumnInfo {
        long nightlightColKey;
        long animationPositionColKey;
        long nightlightPositionColKey;
        long coinsColKey;
        long backgroundColorColKey;
        long brightColKey;
        long rateColKey;
        long addsColKey;
        long currentMelodyColKey;
        long timerTimeColKey;
        long gradientColorColKey;
        long backgroundTumblerColKey;
        long suitCounterColKey;
        long currentMusicColKey;

        MySettingsColumnInfo(OsSchemaInfo schemaInfo) {
            super(14);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("MySettings");
            this.nightlightColKey = addColumnDetails("nightlight", "nightlight", objectSchemaInfo);
            this.animationPositionColKey = addColumnDetails("animationPosition", "animationPosition", objectSchemaInfo);
            this.nightlightPositionColKey = addColumnDetails("nightlightPosition", "nightlightPosition", objectSchemaInfo);
            this.coinsColKey = addColumnDetails("coins", "coins", objectSchemaInfo);
            this.backgroundColorColKey = addColumnDetails("backgroundColor", "backgroundColor", objectSchemaInfo);
            this.brightColKey = addColumnDetails("bright", "bright", objectSchemaInfo);
            this.rateColKey = addColumnDetails("rate", "rate", objectSchemaInfo);
            this.addsColKey = addColumnDetails("adds", "adds", objectSchemaInfo);
            this.currentMelodyColKey = addColumnDetails("currentMelody", "currentMelody", objectSchemaInfo);
            this.timerTimeColKey = addColumnDetails("timerTime", "timerTime", objectSchemaInfo);
            this.gradientColorColKey = addColumnDetails("gradientColor", "gradientColor", objectSchemaInfo);
            this.backgroundTumblerColKey = addColumnDetails("backgroundTumbler", "backgroundTumbler", objectSchemaInfo);
            this.suitCounterColKey = addColumnDetails("suitCounter", "suitCounter", objectSchemaInfo);
            this.currentMusicColKey = addColumnDetails("currentMusic", "currentMusic", objectSchemaInfo);
        }

        MySettingsColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new MySettingsColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final MySettingsColumnInfo src = (MySettingsColumnInfo) rawSrc;
            final MySettingsColumnInfo dst = (MySettingsColumnInfo) rawDst;
            dst.nightlightColKey = src.nightlightColKey;
            dst.animationPositionColKey = src.animationPositionColKey;
            dst.nightlightPositionColKey = src.nightlightPositionColKey;
            dst.coinsColKey = src.coinsColKey;
            dst.backgroundColorColKey = src.backgroundColorColKey;
            dst.brightColKey = src.brightColKey;
            dst.rateColKey = src.rateColKey;
            dst.addsColKey = src.addsColKey;
            dst.currentMelodyColKey = src.currentMelodyColKey;
            dst.timerTimeColKey = src.timerTimeColKey;
            dst.gradientColorColKey = src.gradientColorColKey;
            dst.backgroundTumblerColKey = src.backgroundTumblerColKey;
            dst.suitCounterColKey = src.suitCounterColKey;
            dst.currentMusicColKey = src.currentMusicColKey;
        }
    }

    private static final String NO_ALIAS = "";
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();

    private MySettingsColumnInfo columnInfo;
    private ProxyState<com.appsforkids.pasz.spacelight.RealmObjects.MySettings> proxyState;

    com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (MySettingsColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.appsforkids.pasz.spacelight.RealmObjects.MySettings>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$nightlight() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.nightlightColKey);
    }

    @Override
    public void realmSet$nightlight(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.nightlightColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.nightlightColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$animationPosition() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.animationPositionColKey);
    }

    @Override
    public void realmSet$animationPosition(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.animationPositionColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.animationPositionColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$nightlightPosition() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.nightlightPositionColKey);
    }

    @Override
    public void realmSet$nightlightPosition(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.nightlightPositionColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.nightlightPositionColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$coins() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.coinsColKey);
    }

    @Override
    public void realmSet$coins(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.coinsColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.coinsColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$backgroundColor() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.backgroundColorColKey);
    }

    @Override
    public void realmSet$backgroundColor(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.backgroundColorColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.backgroundColorColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public float realmGet$bright() {
        proxyState.getRealm$realm().checkIfValid();
        return (float) proxyState.getRow$realm().getFloat(columnInfo.brightColKey);
    }

    @Override
    public void realmSet$bright(float value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setFloat(columnInfo.brightColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setFloat(columnInfo.brightColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$rate() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.rateColKey);
    }

    @Override
    public void realmSet$rate(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.rateColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.rateColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public boolean realmGet$adds() {
        proxyState.getRealm$realm().checkIfValid();
        return (boolean) proxyState.getRow$realm().getBoolean(columnInfo.addsColKey);
    }

    @Override
    public void realmSet$adds(boolean value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setBoolean(columnInfo.addsColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setBoolean(columnInfo.addsColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$currentMelody() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.currentMelodyColKey);
    }

    @Override
    public void realmSet$currentMelody(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.currentMelodyColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.currentMelodyColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$timerTime() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.timerTimeColKey);
    }

    @Override
    public void realmSet$timerTime(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.timerTimeColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.timerTimeColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$gradientColor() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.gradientColorColKey);
    }

    @Override
    public void realmSet$gradientColor(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.gradientColorColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.gradientColorColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public boolean realmGet$backgroundTumbler() {
        proxyState.getRealm$realm().checkIfValid();
        return (boolean) proxyState.getRow$realm().getBoolean(columnInfo.backgroundTumblerColKey);
    }

    @Override
    public void realmSet$backgroundTumbler(boolean value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setBoolean(columnInfo.backgroundTumblerColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setBoolean(columnInfo.backgroundTumblerColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$suitCounter() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.suitCounterColKey);
    }

    @Override
    public void realmSet$suitCounter(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.suitCounterColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.suitCounterColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$currentMusic() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.currentMusicColKey);
    }

    @Override
    public void realmSet$currentMusic(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.currentMusicColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.currentMusicColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.currentMusicColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.currentMusicColKey, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(NO_ALIAS, "MySettings", false, 14, 0);
        builder.addPersistedProperty(NO_ALIAS, "nightlight", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "animationPosition", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "nightlightPosition", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "coins", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "backgroundColor", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "bright", RealmFieldType.FLOAT, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "rate", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "adds", RealmFieldType.BOOLEAN, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "currentMelody", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "timerTime", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "gradientColor", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "backgroundTumbler", RealmFieldType.BOOLEAN, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "suitCounter", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "currentMusic", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static MySettingsColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new MySettingsColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "MySettings";
    }

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "MySettings";
    }

    @SuppressWarnings("cast")
    public static com.appsforkids.pasz.spacelight.RealmObjects.MySettings createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.appsforkids.pasz.spacelight.RealmObjects.MySettings obj = realm.createObjectInternal(com.appsforkids.pasz.spacelight.RealmObjects.MySettings.class, true, excludeFields);

        final com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface objProxy = (com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) obj;
        if (json.has("nightlight")) {
            if (json.isNull("nightlight")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'nightlight' to null.");
            } else {
                objProxy.realmSet$nightlight((int) json.getInt("nightlight"));
            }
        }
        if (json.has("animationPosition")) {
            if (json.isNull("animationPosition")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'animationPosition' to null.");
            } else {
                objProxy.realmSet$animationPosition((int) json.getInt("animationPosition"));
            }
        }
        if (json.has("nightlightPosition")) {
            if (json.isNull("nightlightPosition")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'nightlightPosition' to null.");
            } else {
                objProxy.realmSet$nightlightPosition((int) json.getInt("nightlightPosition"));
            }
        }
        if (json.has("coins")) {
            if (json.isNull("coins")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'coins' to null.");
            } else {
                objProxy.realmSet$coins((int) json.getInt("coins"));
            }
        }
        if (json.has("backgroundColor")) {
            if (json.isNull("backgroundColor")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'backgroundColor' to null.");
            } else {
                objProxy.realmSet$backgroundColor((int) json.getInt("backgroundColor"));
            }
        }
        if (json.has("bright")) {
            if (json.isNull("bright")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'bright' to null.");
            } else {
                objProxy.realmSet$bright((float) json.getDouble("bright"));
            }
        }
        if (json.has("rate")) {
            if (json.isNull("rate")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'rate' to null.");
            } else {
                objProxy.realmSet$rate((int) json.getInt("rate"));
            }
        }
        if (json.has("adds")) {
            if (json.isNull("adds")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'adds' to null.");
            } else {
                objProxy.realmSet$adds((boolean) json.getBoolean("adds"));
            }
        }
        if (json.has("currentMelody")) {
            if (json.isNull("currentMelody")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'currentMelody' to null.");
            } else {
                objProxy.realmSet$currentMelody((int) json.getInt("currentMelody"));
            }
        }
        if (json.has("timerTime")) {
            if (json.isNull("timerTime")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'timerTime' to null.");
            } else {
                objProxy.realmSet$timerTime((int) json.getInt("timerTime"));
            }
        }
        if (json.has("gradientColor")) {
            if (json.isNull("gradientColor")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'gradientColor' to null.");
            } else {
                objProxy.realmSet$gradientColor((int) json.getInt("gradientColor"));
            }
        }
        if (json.has("backgroundTumbler")) {
            if (json.isNull("backgroundTumbler")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'backgroundTumbler' to null.");
            } else {
                objProxy.realmSet$backgroundTumbler((boolean) json.getBoolean("backgroundTumbler"));
            }
        }
        if (json.has("suitCounter")) {
            if (json.isNull("suitCounter")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'suitCounter' to null.");
            } else {
                objProxy.realmSet$suitCounter((int) json.getInt("suitCounter"));
            }
        }
        if (json.has("currentMusic")) {
            if (json.isNull("currentMusic")) {
                objProxy.realmSet$currentMusic(null);
            } else {
                objProxy.realmSet$currentMusic((String) json.getString("currentMusic"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.appsforkids.pasz.spacelight.RealmObjects.MySettings createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        final com.appsforkids.pasz.spacelight.RealmObjects.MySettings obj = new com.appsforkids.pasz.spacelight.RealmObjects.MySettings();
        final com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface objProxy = (com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("nightlight")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$nightlight((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'nightlight' to null.");
                }
            } else if (name.equals("animationPosition")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$animationPosition((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'animationPosition' to null.");
                }
            } else if (name.equals("nightlightPosition")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$nightlightPosition((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'nightlightPosition' to null.");
                }
            } else if (name.equals("coins")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$coins((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'coins' to null.");
                }
            } else if (name.equals("backgroundColor")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$backgroundColor((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'backgroundColor' to null.");
                }
            } else if (name.equals("bright")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$bright((float) reader.nextDouble());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'bright' to null.");
                }
            } else if (name.equals("rate")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$rate((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'rate' to null.");
                }
            } else if (name.equals("adds")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$adds((boolean) reader.nextBoolean());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'adds' to null.");
                }
            } else if (name.equals("currentMelody")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$currentMelody((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'currentMelody' to null.");
                }
            } else if (name.equals("timerTime")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$timerTime((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'timerTime' to null.");
                }
            } else if (name.equals("gradientColor")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$gradientColor((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'gradientColor' to null.");
                }
            } else if (name.equals("backgroundTumbler")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$backgroundTumbler((boolean) reader.nextBoolean());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'backgroundTumbler' to null.");
                }
            } else if (name.equals("suitCounter")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$suitCounter((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'suitCounter' to null.");
                }
            } else if (name.equals("currentMusic")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$currentMusic((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$currentMusic(null);
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return realm.copyToRealm(obj);
    }

    static com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxy newProxyInstance(BaseRealm realm, Row row) {
        // Ignore default values to avoid creating unexpected objects from RealmModel/RealmList fields
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        objectContext.set(realm, row, realm.getSchema().getColumnInfo(com.appsforkids.pasz.spacelight.RealmObjects.MySettings.class), false, Collections.<String>emptyList());
        io.realm.com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxy obj = new io.realm.com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxy();
        objectContext.clear();
        return obj;
    }

    public static com.appsforkids.pasz.spacelight.RealmObjects.MySettings copyOrUpdate(Realm realm, MySettingsColumnInfo columnInfo, com.appsforkids.pasz.spacelight.RealmObjects.MySettings object, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
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
            return (com.appsforkids.pasz.spacelight.RealmObjects.MySettings) cachedRealmObject;
        }

        return copy(realm, columnInfo, object, update, cache, flags);
    }

    public static com.appsforkids.pasz.spacelight.RealmObjects.MySettings copy(Realm realm, MySettingsColumnInfo columnInfo, com.appsforkids.pasz.spacelight.RealmObjects.MySettings newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.appsforkids.pasz.spacelight.RealmObjects.MySettings) cachedRealmObject;
        }

        com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface unmanagedSource = (com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) newObject;

        Table table = realm.getTable(com.appsforkids.pasz.spacelight.RealmObjects.MySettings.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, flags);

        // Add all non-"object reference" fields
        builder.addInteger(columnInfo.nightlightColKey, unmanagedSource.realmGet$nightlight());
        builder.addInteger(columnInfo.animationPositionColKey, unmanagedSource.realmGet$animationPosition());
        builder.addInteger(columnInfo.nightlightPositionColKey, unmanagedSource.realmGet$nightlightPosition());
        builder.addInteger(columnInfo.coinsColKey, unmanagedSource.realmGet$coins());
        builder.addInteger(columnInfo.backgroundColorColKey, unmanagedSource.realmGet$backgroundColor());
        builder.addFloat(columnInfo.brightColKey, unmanagedSource.realmGet$bright());
        builder.addInteger(columnInfo.rateColKey, unmanagedSource.realmGet$rate());
        builder.addBoolean(columnInfo.addsColKey, unmanagedSource.realmGet$adds());
        builder.addInteger(columnInfo.currentMelodyColKey, unmanagedSource.realmGet$currentMelody());
        builder.addInteger(columnInfo.timerTimeColKey, unmanagedSource.realmGet$timerTime());
        builder.addInteger(columnInfo.gradientColorColKey, unmanagedSource.realmGet$gradientColor());
        builder.addBoolean(columnInfo.backgroundTumblerColKey, unmanagedSource.realmGet$backgroundTumbler());
        builder.addInteger(columnInfo.suitCounterColKey, unmanagedSource.realmGet$suitCounter());
        builder.addString(columnInfo.currentMusicColKey, unmanagedSource.realmGet$currentMusic());

        // Create the underlying object and cache it before setting any object/objectlist references
        // This will allow us to break any circular dependencies by using the object cache.
        Row row = builder.createNewObject();
        io.realm.com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxy managedCopy = newProxyInstance(realm, row);
        cache.put(newObject, managedCopy);

        return managedCopy;
    }

    public static long insert(Realm realm, com.appsforkids.pasz.spacelight.RealmObjects.MySettings object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey();
        }
        Table table = realm.getTable(com.appsforkids.pasz.spacelight.RealmObjects.MySettings.class);
        long tableNativePtr = table.getNativePtr();
        MySettingsColumnInfo columnInfo = (MySettingsColumnInfo) realm.getSchema().getColumnInfo(com.appsforkids.pasz.spacelight.RealmObjects.MySettings.class);
        long objKey = OsObject.createRow(table);
        cache.put(object, objKey);
        Table.nativeSetLong(tableNativePtr, columnInfo.nightlightColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$nightlight(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.animationPositionColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$animationPosition(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.nightlightPositionColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$nightlightPosition(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.coinsColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$coins(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.backgroundColorColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$backgroundColor(), false);
        Table.nativeSetFloat(tableNativePtr, columnInfo.brightColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$bright(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.rateColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$rate(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.addsColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$adds(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.currentMelodyColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$currentMelody(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.timerTimeColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$timerTime(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.gradientColorColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$gradientColor(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.backgroundTumblerColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$backgroundTumbler(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.suitCounterColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$suitCounter(), false);
        String realmGet$currentMusic = ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$currentMusic();
        if (realmGet$currentMusic != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.currentMusicColKey, objKey, realmGet$currentMusic, false);
        }
        return objKey;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.appsforkids.pasz.spacelight.RealmObjects.MySettings.class);
        long tableNativePtr = table.getNativePtr();
        MySettingsColumnInfo columnInfo = (MySettingsColumnInfo) realm.getSchema().getColumnInfo(com.appsforkids.pasz.spacelight.RealmObjects.MySettings.class);
        com.appsforkids.pasz.spacelight.RealmObjects.MySettings object = null;
        while (objects.hasNext()) {
            object = (com.appsforkids.pasz.spacelight.RealmObjects.MySettings) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey());
                continue;
            }
            long objKey = OsObject.createRow(table);
            cache.put(object, objKey);
            Table.nativeSetLong(tableNativePtr, columnInfo.nightlightColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$nightlight(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.animationPositionColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$animationPosition(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.nightlightPositionColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$nightlightPosition(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.coinsColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$coins(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.backgroundColorColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$backgroundColor(), false);
            Table.nativeSetFloat(tableNativePtr, columnInfo.brightColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$bright(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.rateColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$rate(), false);
            Table.nativeSetBoolean(tableNativePtr, columnInfo.addsColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$adds(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.currentMelodyColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$currentMelody(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.timerTimeColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$timerTime(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.gradientColorColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$gradientColor(), false);
            Table.nativeSetBoolean(tableNativePtr, columnInfo.backgroundTumblerColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$backgroundTumbler(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.suitCounterColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$suitCounter(), false);
            String realmGet$currentMusic = ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$currentMusic();
            if (realmGet$currentMusic != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.currentMusicColKey, objKey, realmGet$currentMusic, false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.appsforkids.pasz.spacelight.RealmObjects.MySettings object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey();
        }
        Table table = realm.getTable(com.appsforkids.pasz.spacelight.RealmObjects.MySettings.class);
        long tableNativePtr = table.getNativePtr();
        MySettingsColumnInfo columnInfo = (MySettingsColumnInfo) realm.getSchema().getColumnInfo(com.appsforkids.pasz.spacelight.RealmObjects.MySettings.class);
        long objKey = OsObject.createRow(table);
        cache.put(object, objKey);
        Table.nativeSetLong(tableNativePtr, columnInfo.nightlightColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$nightlight(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.animationPositionColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$animationPosition(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.nightlightPositionColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$nightlightPosition(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.coinsColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$coins(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.backgroundColorColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$backgroundColor(), false);
        Table.nativeSetFloat(tableNativePtr, columnInfo.brightColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$bright(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.rateColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$rate(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.addsColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$adds(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.currentMelodyColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$currentMelody(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.timerTimeColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$timerTime(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.gradientColorColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$gradientColor(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.backgroundTumblerColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$backgroundTumbler(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.suitCounterColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$suitCounter(), false);
        String realmGet$currentMusic = ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$currentMusic();
        if (realmGet$currentMusic != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.currentMusicColKey, objKey, realmGet$currentMusic, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.currentMusicColKey, objKey, false);
        }
        return objKey;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.appsforkids.pasz.spacelight.RealmObjects.MySettings.class);
        long tableNativePtr = table.getNativePtr();
        MySettingsColumnInfo columnInfo = (MySettingsColumnInfo) realm.getSchema().getColumnInfo(com.appsforkids.pasz.spacelight.RealmObjects.MySettings.class);
        com.appsforkids.pasz.spacelight.RealmObjects.MySettings object = null;
        while (objects.hasNext()) {
            object = (com.appsforkids.pasz.spacelight.RealmObjects.MySettings) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey());
                continue;
            }
            long objKey = OsObject.createRow(table);
            cache.put(object, objKey);
            Table.nativeSetLong(tableNativePtr, columnInfo.nightlightColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$nightlight(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.animationPositionColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$animationPosition(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.nightlightPositionColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$nightlightPosition(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.coinsColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$coins(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.backgroundColorColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$backgroundColor(), false);
            Table.nativeSetFloat(tableNativePtr, columnInfo.brightColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$bright(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.rateColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$rate(), false);
            Table.nativeSetBoolean(tableNativePtr, columnInfo.addsColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$adds(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.currentMelodyColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$currentMelody(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.timerTimeColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$timerTime(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.gradientColorColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$gradientColor(), false);
            Table.nativeSetBoolean(tableNativePtr, columnInfo.backgroundTumblerColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$backgroundTumbler(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.suitCounterColKey, objKey, ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$suitCounter(), false);
            String realmGet$currentMusic = ((com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) object).realmGet$currentMusic();
            if (realmGet$currentMusic != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.currentMusicColKey, objKey, realmGet$currentMusic, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.currentMusicColKey, objKey, false);
            }
        }
    }

    public static com.appsforkids.pasz.spacelight.RealmObjects.MySettings createDetachedCopy(com.appsforkids.pasz.spacelight.RealmObjects.MySettings realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.appsforkids.pasz.spacelight.RealmObjects.MySettings unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.appsforkids.pasz.spacelight.RealmObjects.MySettings();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.appsforkids.pasz.spacelight.RealmObjects.MySettings) cachedObject.object;
            }
            unmanagedObject = (com.appsforkids.pasz.spacelight.RealmObjects.MySettings) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface unmanagedCopy = (com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) unmanagedObject;
        com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface realmSource = (com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxyInterface) realmObject;
        Realm objectRealm = (Realm) ((RealmObjectProxy) realmObject).realmGet$proxyState().getRealm$realm();
        unmanagedCopy.realmSet$nightlight(realmSource.realmGet$nightlight());
        unmanagedCopy.realmSet$animationPosition(realmSource.realmGet$animationPosition());
        unmanagedCopy.realmSet$nightlightPosition(realmSource.realmGet$nightlightPosition());
        unmanagedCopy.realmSet$coins(realmSource.realmGet$coins());
        unmanagedCopy.realmSet$backgroundColor(realmSource.realmGet$backgroundColor());
        unmanagedCopy.realmSet$bright(realmSource.realmGet$bright());
        unmanagedCopy.realmSet$rate(realmSource.realmGet$rate());
        unmanagedCopy.realmSet$adds(realmSource.realmGet$adds());
        unmanagedCopy.realmSet$currentMelody(realmSource.realmGet$currentMelody());
        unmanagedCopy.realmSet$timerTime(realmSource.realmGet$timerTime());
        unmanagedCopy.realmSet$gradientColor(realmSource.realmGet$gradientColor());
        unmanagedCopy.realmSet$backgroundTumbler(realmSource.realmGet$backgroundTumbler());
        unmanagedCopy.realmSet$suitCounter(realmSource.realmGet$suitCounter());
        unmanagedCopy.realmSet$currentMusic(realmSource.realmGet$currentMusic());

        return unmanagedObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("MySettings = proxy[");
        stringBuilder.append("{nightlight:");
        stringBuilder.append(realmGet$nightlight());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{animationPosition:");
        stringBuilder.append(realmGet$animationPosition());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{nightlightPosition:");
        stringBuilder.append(realmGet$nightlightPosition());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{coins:");
        stringBuilder.append(realmGet$coins());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{backgroundColor:");
        stringBuilder.append(realmGet$backgroundColor());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{bright:");
        stringBuilder.append(realmGet$bright());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{rate:");
        stringBuilder.append(realmGet$rate());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{adds:");
        stringBuilder.append(realmGet$adds());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{currentMelody:");
        stringBuilder.append(realmGet$currentMelody());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{timerTime:");
        stringBuilder.append(realmGet$timerTime());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{gradientColor:");
        stringBuilder.append(realmGet$gradientColor());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{backgroundTumbler:");
        stringBuilder.append(realmGet$backgroundTumbler());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{suitCounter:");
        stringBuilder.append(realmGet$suitCounter());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{currentMusic:");
        stringBuilder.append(realmGet$currentMusic() != null ? realmGet$currentMusic() : "null");
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
        com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxy aMySettings = (com_appsforkids_pasz_spacelight_RealmObjects_MySettingsRealmProxy)o;

        BaseRealm realm = proxyState.getRealm$realm();
        BaseRealm otherRealm = aMySettings.proxyState.getRealm$realm();
        String path = realm.getPath();
        String otherPath = otherRealm.getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;
        if (realm.isFrozen() != otherRealm.isFrozen()) return false;
        if (!realm.sharedRealm.getVersionID().equals(otherRealm.sharedRealm.getVersionID())) {
            return false;
        }

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aMySettings.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getObjectKey() != aMySettings.proxyState.getRow$realm().getObjectKey()) return false;

        return true;
    }
}
