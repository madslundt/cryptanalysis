using System;
using System.Text;
using System.IO;

namespace Project3
{
	class MainClass
	{
		private static readonly string START_DATE = "2009-06-22 00:00:00";
		private static readonly string END_DATE = "2009-06-28 23:59:59";
		/*
		 * Since we split ' ' every word have to be seperated
		 * We can pick a lot of words as long as they are long enough and make sense
		 * If they are long enough the chance for them being there as a coincidence is smaller
		 */
		private static readonly string[] CHECK_WORDS =
		{"NSA", "National", "Security", "Agency", "Barack","Obama", "Europe", "United",
		"States", "America", "U.S.", "USA", "Edward", "Snowden", "Chuck", "Hagel"};
		private static readonly DateTime EPOCH = new DateTime(1970, 1, 1, 0, 0, 0, DateTimeKind.Utc);
		private static readonly int KEY_LENGTH = 16;
		private static readonly int WORDS_OCCURENCE = 1;


		public static void Main (string[] args)
		{
			byte[] ciphertext = loadFile(@"ciphertext_sheet3.txt");

			String plainText; //the decrypted text
			long start = getTimestamp(START_DATE);
			long end = getTimestamp(END_DATE);
			int[] key;
			int textCounter; //used to know which position in the text we are at

			// Running through all the dates
			for (long i = start; i < end; i++) 
			{
				if ((i - start) % 1000 == 0)
				{
				Console.WriteLine("At key " + (i - start));
				}

				key = calculateKey (i);
				textCounter = 0;
				plainText = "";
				String insert;
				//for each loop decrypting the text with the calculated key
				foreach (byte c in ciphertext)
				{

					try {
						insert = decryption(Convert.ToInt32(c), key, textCounter);
					} catch(Exception) {
						//Console.WriteLine (e);
						break;
					}
					plainText += insert;
					textCounter++;
				}
				//Checking if the plaintext makes sense
				if (check_text(plainText))
				{
					File.WriteAllText(@"plainttext_sheet3.txt", plainText);
					Console.WriteLine(plainText);
					break;
				}
			}
		}

		public static bool check_text (String text)
		{
			int count = 0;
			foreach (string w in CHECK_WORDS)
			{
				if (text.Contains (w))
				{
					//finding how many times the word appears in the text
					count += (text.Length - text.Replace (w, "").Length) / w.Length;
					//count++;
				}
			}
			// if the words appears more than 5 times return true.
			if (count > WORDS_OCCURENCE)
			{
				return true;
			}
			return false;
		}

		//function calculating the key we try to decrypt the ciphertext with. 
		public static int[] calculateKey(long timeStamp)
		{
			int[] key = new int[KEY_LENGTH];
			long s0 = updateFunction (timeStamp);

			for (int i = 0; i < KEY_LENGTH; i++) 
			{
				s0 = updateFunction (s0);
				key[i] = bitCut(updateFunction(s0));
			
			}
			return key;
		}

		//inputs a long and return the 8 least significant bits
		public static int bitCut(long input)
		{
			String bitString = Convert.ToString(input, 2);
			bitString = bitString.Substring (bitString.Length - 8, 8);
			return Convert.ToInt32 (bitString, 2);
		}

		//Function used to update the value in the key
		public static long updateFunction(long s) 
		{
			return (69069 * s + 5) % (long) Math.Pow (2.0, 32.0);
		}

		/*
		 * Taking in a char, using the key and the i to decrypt it
		 * This is gives an integer in unicode
		 * The unicode translated to a string
		 */
		public static String decryption (int c, int[] k, int i)
		{
			int decrypted = c ^ k [i % KEY_LENGTH];
			/*
			 * If the decrypted int, is outside of the unicode of the american symbols
			 * it returns some random we know cannot be there.
			 * This is to limit the run time. Might need change.
			 */
			if (decrypted > 150 || decrypted < 10)
			{
				throw new Exception ("Outside unicode");
			}
			return Char.ConvertFromUtf32(decrypted);
		}

		public static byte[] loadFile(String path) 
		{
			//return System.IO.File.ReadAllText(path);
			return File.ReadAllBytes (path);

		}

		/// <summary>
		/// Converts a string into a DateTime, then returns the time in seconds elapsed from 1970-1-1 00:00:00.
		/// </summary>
		/// <returns>
		/// Time elapsed from given date to 1970-1-1 00:00:00
		/// </returns>
		/// <param name='date'>
		/// Date & time as a string. Require the string input "1970-1-1 00:00:00".
		/// </param>
		public static long getTimestamp(String date)
		{
			String newDate = date.Split(' ')[0]; //gets the date
			String newTime = date.Split(' ')[1]; //gets the time
			String[] dateSplit = newDate.Split('-'); //gets [years, months, days]
			String[] timeSplit = newTime.Split(':'); //gets [hours, minutes, seconds]
			//converting from string to ints
			int year = Convert.ToInt32(dateSplit[0]);
			int month = Convert.ToInt32(dateSplit[1]);
			int day = Convert.ToInt32(dateSplit[2]);
			int hour = Convert.ToInt32(timeSplit[0]);
			int minute = Convert.ToInt32(timeSplit[1]);
			int second = Convert.ToInt32(timeSplit[2]);
			//creating a DateTime with these values
			DateTime value = new DateTime(year, month, day, hour, minute, second);
			//subtracting 1970-1-1 00:00:00 and returning the elapsed time
			TimeSpan elapsedTime =  value.Subtract(EPOCH);
			return (long) elapsedTime.TotalSeconds;

		}
	}
}
