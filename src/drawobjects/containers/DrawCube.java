/*
	Die Eckpunkte des Cubes sind nach folgendem Schema durchnummeriert:
		- Die Nummerierung beginnt mir 0
		- Die Nummerierung fängt bei der im Konstruktor übergeben <position> an
		- Die Nummerierung läuft einmal im Kreis au der Ebene der <position>
		- Die Nummerierung läuft einmal im Kreis au der Ebene der <position>
		- Danach werden alle Punkte in der zweiten Ebene durchnummeriert und zwar so, dass Punkt4 über Punkt0 liegt, Punkt5 über Punkt1 ...
*/

package drawobjects.containers;

import drawobjects.containers.Polygon;
import misc.math.Vec3D;
import misc.Debug;

public final class DrawCube extends Polygon
{
	public DrawCube(String name, Vec3D position, Vec3D size)
	{
		super(name);
		initComponents(position, size);
	}

	@Override protected void initComponents() {}

	private void definePoints(Vec3D position, Vec3D size)
	{
		Vec3D aim = new Vec3D(position);
		aim.addWith(size); // Vektor der die Position angibt, die sich aus position + size ergibt

		for (int i = 0; i < getPoints().size(); i++)
		{
			if (getPoints().get(i) == null)
			{
				Debug.warn("Cube.definePoints(): getPoints().get(" + i + ") == null; Maybe not created before");
			}
		}

		// Punkte hinzufügen
		// Ebene 1
		getPoints().get(0).copy(position);
		getPoints().get(1).setAll(position.getX(), aim.getY(), position.getZ());
		getPoints().get(2).setAll(aim.getX(), aim.getY(), position.getZ());
		getPoints().get(3).setAll(aim.getX(), position.getY(), position.getZ());
		// Ebene 2
		getPoints().get(4).setAll(position.getX(), position.getY(), aim.getZ());
		getPoints().get(5).setAll(position.getX(), aim.getY(), aim.getZ());
		getPoints().get(6).copy(aim);
		getPoints().get(7).setAll(aim.getX(), position.getY(), aim.getZ());
	}

	private void createPoints(Vec3D position, Vec3D size)
	{
		for (int i = 0; i < 8; i++)
		{
			getPoints().add(new Vec3D());
		}
		definePoints(position, size);
	}

	public void moveTo(Vec3D vec)
	{
		Debug.warn("DrawCube.moveTo(): TODO");
	}

	public void move(Vec3D vec)
	{
		Debug.warn("DrawCube.move(): TODO");
	}

	protected final void initComponents(Vec3D position, Vec3D size)
	{
		createPoints(position, size);

		// Linien hinzufügen
		// Linien werden in components hinzugefügt
		// Ebene 1
		addLine(0,1);
		addLine(1,2);
		addLine(2,3);
		addLine(3,0);
		// Ebene 2
		addLine(4,5);
		addLine(5,6);
		addLine(6,7);
		addLine(7,4);
		// Senkrechten
		addLine(0,4);
		addLine(1,5);
		addLine(2,6);
		addLine(3,7);
	}
}
