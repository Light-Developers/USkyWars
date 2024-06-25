package com.ultimismc.skywars.core.storage;


import com.ultimismc.skywars.core.user.User;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;


import java.util.UUID;

public class SkyUserCodec implements Codec<User> {


    @Override
    public User decode(BsonReader reader, DecoderContext decoderContext) {
        UUID uuid = UUID.fromString(reader.readString("uuid"));
        User skyUser = new User(uuid);
        skyUser.setName(reader.readString("name"));
        //skyUser.setLevel(new Level());
       // SoloUserStats decode = statsCodec.decode(reader, decoderContext);
      //  skyUser.setSoloUserStats(decode);
        return skyUser;
    }

    @Override
    public void encode(BsonWriter writer, User skyUser, EncoderContext encoderContext) {
        writer.writeString("uuid", skyUser.getUuid().toString());
        writer.writeString("name", skyUser.getName());
    }

    @Override
    public Class<User> getEncoderClass() {
        return User.class;
    }
}
