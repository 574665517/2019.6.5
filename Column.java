package cn.tedu.game;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Column {
	int x, y;// ����
	int width, height;
	BufferedImage image;
	int gap;// �������Ӽ�϶
	int distance;// ˮƽ�������Ӽ�ľ���

	public Column(int n) {
		try {
			image = ImageIO.read(getClass().getResource("ywx column.png"));
			width = image.getWidth();
			height = image.getHeight();
			gap = 144;
			distance = 245;
			x = 550 + (n - 1) * distance;
			y = (int) (Math.random() * 218) + 132;

		} catch (Exception e) {
		}
	}

	public void run() {
		x--;
		if (x <= width / 2) {
			x = distance * 2 - width / 2;
			y = (int) (Math.random() * 218) + 132;
		}
	}

}