package thewizardmod.runes;

import net.minecraft.util.IStringSerializable;

public class EnumRunes {

	public static enum RuneTypes implements IStringSerializable{
		FEHU("fehu",0,"Cattle"),
		URUZ("uruz",1, "Brute strenght"),
		THURISAZ("thurisaz",2,"Seeing of future"),
		ANSUZ("ansuz",3,"Reference to Odin"),
		RAIDO("raido",4,"Journey"),
		KAUNAN("kaunan",5,"Torch"),
		GIBO("gibo",6,"Gift of harmonic relationship"),
		WUNJO("wunjo",7,"Bliss and glory"),
		HAGLAZ("haglaz",8,"Destructive forces"),
		NAUDIZ("naudiz",9,"Negatives of human needs"),
		EISA("eisa",10,"Stuck"),
		JERAN("jeran",11,"Harvest time"),
		IHWAZ("ihwaz",12,"Transformation"),
		PERTHO("pertho",13,"Initiation, Something hidden"),
		ALGIZ("algiz",14,"Defence"),
		SOWULO("sowulo",15,"Sun"),
		TEIWAZ("teiwaz",16,"Sky god TYR"),
		BERKO("berko",17,"New beginn"),
		EHAWAZ("ehwaz",18,"Sacred horse"),
		MANN("mann",19,"Nature of humanity"),
		LAGUZ("laguz",20,"Water, Emotions"),
		INGWAZ("ingwaz",21,"Inspiration, Fertitlity"),
		OTHALAN("othalan",22,"Inheritance"),
		DAGAZ("dagaz",23,"Daylight or dawn"),
		BLANK("blank",24,"Rune of Odin");

		private int ID;
		private String name;
		private String description;
		
		private RuneTypes(String name, int ID, String description){
			this.ID = ID;
			this.name=name;
			this.description=description;
			
		}
		
		@Override
		public String getName() {
			return this.name;
		}
		
		public int getID(){
			return this.ID;
		}
		
		public String getDescription(){
			return this.description;
		}
		
		
	}
}
