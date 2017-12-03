/**
 * ���������� ��������� ����� ���������� � ����� ������ 
 * buildings.net.server.sequental.BinaryServer, ���������� 
 * ����� main(). ������ ������ ��������� ���������������� 
 * ��������� �������� � ������������ � ��������� ����� 
 * ���������� ������� ���������� � ��������� �����.
 * ������ ��������� ������ ��������� ��� ����� ���� �������� 
 * ��������� ������, ���������� �� 1000$ ��� ������ ����, 
 * �� 1500$ ��� ����� � �� 2000$ ��� ����� ���������.
 * ��������� ������ ��������� ������ ������� ����������� 
 * � ���� ���������� ������ ������ �������.
 * ��� ���������� ������ �� ������ ������������� ������������ 
 * ������ ������ Buildings, ������� �������� ��������� ������.
 */
package buildings.net.server.sequental;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import buildings.Buildings;
import buildings.factory.HotelFactory;
import buildings.factory.OfficeFactory;
import buildings.interfaces.Building;

public class BinaryServer {
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket();
		Socket client = server.accept();
		
		DataInputStream in = new DataInputStream(client.getInputStream());
		DataOutputStream out = new DataOutputStream(client.getOutputStream());
		
		while(!client.isClosed()) {
			
			out.flush();
		}
		in.close();
		out.close();
		client.close();
		System.out.println("Connection and channels were closed");
	}
}
