package org.ekber.utils;

import java.io.*;
import java.net.URL;
import java.util.Vector;

public class ThreadPool {

	Vector<String> jobs = new Vector<String>();
	int threadNum = 4, fileIndex = 0, doneThread = 0;
	boolean finish = false;

	public synchronized void done() {
		finish = true;
	}

	public synchronized boolean isFinish() {
		return finish;
	}

	public synchronized String getJob() {
		try {
			if (jobs.isEmpty()) {
				if (++doneThread == threadNum && isFinish()) {
					System.out.println("\nDosya indirmeleri tamamlandı.");
					System.exit(0);
				}
				wait();
				doneThread--;
			}
		} catch (Exception e) {
			System.out.println("getJob Error" + e);
		}
		return (String) jobs.remove(0);
	}

	public synchronized void putJob(String fileURL) {
		try {
			jobs.addElement(fileURL);
			notify();
		} catch (Exception e) {
			System.out.println("putJob Error");
		}
	}

	public ThreadPool(String[] args) throws Exception {
		if (args[0].charAt(0) == '-') {
			threadNum = Integer.parseInt(args[0].substring(1));
			fileIndex = 1;
		}
		for (int numOfThreads = 0; numOfThreads < threadNum; numOfThreads++) {
			ReceiverThread curThread = new ReceiverThread(numOfThreads + 1);
			curThread.start();
		}

		System.out.println("\t\t" + threadNum + " tane thread başladı.");
		String fileURL;

		// This loop goes through all the filenames given as arguments
		for (int files = fileIndex; files < args.length; files++) {

			System.out.println("\t\t" + args[files] + " dosyası açıldı.");
			BufferedReader sites = new BufferedReader(new FileReader(
					args[files]));
			// For each URL in the file this loop creates a new ReceiverThread
			while ((fileURL = sites.readLine()) != null) {
				putJob(fileURL);
			}
		}
		done();
	}

	public static void main(String[] args) throws Exception {
		new ThreadPool(args);
	}

	class ReceiverThread extends Thread {
		int amount, id;
		BufferedInputStream in;
		FileOutputStream out;
		byte[] buffer;
		String file;

		public ReceiverThread(int tid) throws Exception {
			id = tid;
		}

		public void run() {

			while (true) {

				try {

					URL u = new URL(getJob());
					String filepath = u.getFile();
					file = filepath.substring(filepath.lastIndexOf("/") + 1);
					in = new BufferedInputStream(u.openStream());
					out = new FileOutputStream(file);
					buffer = new byte[1000];

					System.out.println("Thread " + id + " " + file
							+ " dosyası için başlıyor.");

					while ((amount = in.read(buffer)) != -1) {
						out.write(buffer, 0, amount);
					}

					in.close();
					out.close();

				} catch (Exception e) {
					System.out.println("Error occured.");
				}

				System.out.println("Thread " + id + " " + file
						+ " dosyasını tamamladı.");
			}
		} // run
	}
}
