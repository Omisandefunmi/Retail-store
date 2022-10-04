package africa.semicolon.retailstore.services.cloud;

import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service("cloudinary-service")
@RequiredArgsConstructor
public class CloudServiceImpl implements CloudService{


    private final Cloudinary cloudinary;

    @Override
    public Map<?, ?> upload(byte[] bytes, Map<?, ?> params) throws IOException {
        return cloudinary.uploader().upload(bytes, params);
    }
}
