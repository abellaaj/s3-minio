package aiman.bellaaj.s3;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import io.minio.MinioClient;
import io.minio.Result;
import io.minio.errors.MinioException;
import io.minio.messages.Bucket;
import io.minio.messages.Item;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args)
			throws NoSuchAlgorithmException, IOException, InvalidKeyException, XmlPullParserException {
		fileUpload();
		//listBucket();
		//listObject();
		//removeBucket();
	}

	private static void fileUpload()
			throws NoSuchAlgorithmException, IOException, InvalidKeyException, XmlPullParserException {
		try {
			// Create a minioClient with the MinIO Server name, Port, Access key and Secret
			// key.
			//MinioClient minioClient = new MinioClient("http://127.0.0.1:9000", "minioadmin", "minioadmin");
			
			MinioClient minioClient = new MinioClient("https://s3.amazonaws.com", "AKIAZOEAWRGFV7BR4LHR", "UoQvlUijl+Rr+yJlF9fEMuR/1jwQVhlUM07Rp0me");

			// Check if the bucket already exists.
			boolean isExist = minioClient.bucketExists("test-xa");
			if (isExist) {
				System.out.println("Bucket already exists.");
			} else {
				// Make a new bucket called asiatrip to hold a zip file of photos.
				minioClient.makeBucket("asiatrip");
			}

			// Upload the zip file to the bucket with putObject
			minioClient.putObject("test-xa", "aiman.txt", "/home/vagrant/Downloads/test.txt");
			System.out.println(
					"/home/vagrant/Downloads/test.txt is successfully uploaded as asiaphotos.zip to `asiatrip` bucket.");
		} catch (MinioException e) {
			System.out.println("Error occurred: " + e);
		}
	}
	
	private static void listBucket()
			throws NoSuchAlgorithmException, IOException, InvalidKeyException, XmlPullParserException {
		try {
			// Create a minioClient with the MinIO Server name, Port, Access key and Secret
			// key.
			MinioClient minioClient = new MinioClient("http://127.0.0.1:9000", "minioadmin", "minioadmin");

			 /* Amazon S3: */
		      // MinioClient minioClient = new MinioClient("https://s3.amazonaws.com", "YOUR-ACCESSKEYID",
		      //                                           "YOUR-SECRETACCESSKEY");

		      // List buckets we have atleast read access.
		      List<Bucket> bucketList = minioClient.listBuckets();
		      for (Bucket bucket : bucketList) {
		        System.out.println(bucket.creationDate() + ", " + bucket.name());
		      }
		    } catch (MinioException e) {
		      System.out.println("Error occurred: " + e);
		    }
	}
	
	private static void listObject()
			throws NoSuchAlgorithmException, IOException, InvalidKeyException, XmlPullParserException {
		try {
			// Create a minioClient with the MinIO Server name, Port, Access key and Secret
			// key.
//			MinioClient minioClient = new MinioClient("http://127.0.0.1:9000", "minioadmin", "minioadmin");

			MinioClient minioClient = new MinioClient("https://s3.amazonaws.com", "AKIAZOEAWRGFV7BR4LHR", "UoQvlUijl+Rr+yJlF9fEMuR/1jwQVhlUM07Rp0me");

			
			/* Amazon S3: */
		      // MinioClient miniolistObject();
			//removeBClient = new MinioClient("https://s3.amazonaws.com", "YOUR-ACCESSKEYID",
		      //                                           "YOUR-SECRETACCESSKEY");

		      // Check whether 'my-bucketname' exist or not.
		      boolean found = minioClient.bucketExists("test-xa");
		      if (found) {
		        // List objects frlistObject();
		  		//removeBom 'my-bucketname'
		        Iterable<Result<Item>> myObjects = minioClient.listObjects("test-xa");
		        for (Result<Item> result : myObjects) {
		          Item item = result.get();
		          System.out.println(item.lastModified() + ", " + item.size() + ", " + item.objectName());
		        }
		      } else {
		        System.out.println("my bucket test does not exist");
		      }
		    } catch (MinioException e) {
		      System.out.println("Error occurred: " + e);
		    }
	}
	
	private static void removeBucket()
			throws NoSuchAlgorithmException, IOException, InvalidKeyException, XmlPullParserException {
		try {
			// Create a minioClient with the MinIO Server name, Port, Access key and Secret
			// key.
			MinioClient minioClient = new MinioClient("http://127.0.0.1:9000", "minioadmin", "minioadmin");

			 /* Amazon S3: */
		      // MinioClient minioClient = new MinioClient("https://s3.amazonaws.com", "YOUR-ACCESSKEYID",
		      //                                           "YOUR-SECRETACCESSKEY");

		      // Remove bucket 'my-bucketname' if it exists.
		      // This operation will only work if your bucket is empty.
		      boolean found = minioClient.bucketExists("asiatrip");
		      if (found) {
		        minioClient.removeBucket("asiatrip");
		        System.out.println("asiatrip is removed successfully");
		      } else {
		        System.out.println("asiatrip does not exist");
		      }
		    } catch (MinioException e) {
		      System.out.println("Error occurred: " + e);
		    }
	}
}
