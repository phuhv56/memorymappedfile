package file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reader
{
    private String fileName;

    public Path getFileURIFromResources(String filename)
    {
        Path path = Paths.get(getClass().getClassLoader().getResource(filename).getPath());
        return path;
    }

    public void readFile(int page, int size) throws IOException
    {
        CharBuffer charBuffer = null;
        Path pathToRead = getFileURIFromResources(this.fileName);

        try (FileChannel fileChannel = FileChannel.open(pathToRead, StandardOpenOption.READ))
        {

            MappedByteBuffer mappedByteBuffer = fileChannel
                    .map(FileChannel.MapMode.READ_ONLY, page*size, size);

            if (mappedByteBuffer != null)
            {
                charBuffer = Charset.forName("UTF-8").decode(mappedByteBuffer);
                System.out.println(charBuffer.toString());
            }
        }
    }
}
