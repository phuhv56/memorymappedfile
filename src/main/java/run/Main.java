package run;

import configuration.ReadTask;
import configuration.WorkerExecutor;
import file.Reader;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        WorkerExecutor workerExecutor = new WorkerExecutor();

        for (int i = 0 ; i <= 10 ; i ++) {
            workerExecutor.execute(new ReadTask());
        }


        workerExecutor.terminated();
    }


}
