package thewizardmod.runes;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class StartupCommon
{
	  public static Fehu fehu;
	  public static Uruz uruz;
	  public static Thurisaz thurisaz;
	  public static Ansuz ansuz;
	  public static Raido raido;
	  public static Kaunan kaunan;
	  public static Gibo gibo;
	  public static Wunjo wunjo;
	  public static Haglaz haglaz;
	  public static Naudiz naudiz;
	  public static Eisa eisa;
	  public static Jeran jeran;
	  public static Ihwaz ihwaz;
	  public static Pertho pertho;
	  public static Algiz algiz;
	  public static Sowulo sowulo;
	  public static Teiwaz teiwaz;
	  public static Berko berko;
	  public static Ehwaz ehwaz;
	  public static Mann mann;
	  public static Laguz laguz;
	  public static Ingwaz ingwaz;
	  public static Othalan othalan;
	  public static Dagaz dagaz;
	  public static Blank blank;
	  
	  
  public static void preInitCommon()
  {

	    fehu = (Fehu)(new Fehu().setUnlocalizedName("tw_rune_fehu"));
	    fehu.setRegistryName("rune_fehu_registry_name");
	    GameRegistry.register(fehu);

	    uruz = (Uruz)(new Uruz().setUnlocalizedName("tw_rune_uruz"));
	    uruz.setRegistryName("rune_uruz_registry_name");
	    GameRegistry.register(uruz);

	    thurisaz = (Thurisaz)(new Thurisaz().setUnlocalizedName("tw_rune_thurisaz"));
	    thurisaz.setRegistryName("rune_thurisaz_registry_name");
	    GameRegistry.register(thurisaz);

	    ansuz = (Ansuz)(new Ansuz().setUnlocalizedName("tw_rune_ansuz"));
	    ansuz.setRegistryName("rune_ansuz_registry_name");
	    GameRegistry.register(ansuz);

	    raido = (Raido)(new Raido().setUnlocalizedName("tw_rune_raido"));
	    raido.setRegistryName("rune_raido_registry_name");
	    GameRegistry.register(raido);

	    kaunan = (Kaunan)(new Kaunan().setUnlocalizedName("tw_rune_kaunan"));
	    kaunan.setRegistryName("rune_kaunan_registry_name");
	    GameRegistry.register(kaunan);

	    gibo = (Gibo)(new Gibo().setUnlocalizedName("tw_rune_gibo"));
	    gibo.setRegistryName("rune_gibo_registry_name");
	    GameRegistry.register(gibo);

	    wunjo = (Wunjo)(new Wunjo().setUnlocalizedName("tw_rune_wunjo"));
	    wunjo.setRegistryName("rune_wunjo_registry_name");
	    GameRegistry.register(wunjo);

	    haglaz = (Haglaz)(new Haglaz().setUnlocalizedName("tw_rune_haglaz"));
	    haglaz.setRegistryName("rune_haglaz_registry_name");
	    GameRegistry.register(haglaz);

	    naudiz = (Naudiz)(new Naudiz().setUnlocalizedName("tw_rune_naudiz"));
	    naudiz.setRegistryName("rune_naudiz_registry_name");
	    GameRegistry.register(naudiz);

	    eisa = (Eisa)(new Eisa().setUnlocalizedName("tw_rune_eisa"));
	    eisa.setRegistryName("rune_eisa_registry_name");
	    GameRegistry.register(eisa);

	    jeran = (Jeran)(new Jeran().setUnlocalizedName("tw_rune_jeran"));
	    jeran.setRegistryName("rune_jeran_registry_name");
	    GameRegistry.register(jeran);

	    ihwaz = (Ihwaz)(new Ihwaz().setUnlocalizedName("tw_rune_ihwaz"));
	    ihwaz.setRegistryName("rune_ihwaz_registry_name");
	    GameRegistry.register(ihwaz);

	    pertho = (Pertho)(new Pertho().setUnlocalizedName("tw_rune_pertho"));
	    pertho.setRegistryName("rune_pertho_registry_name");
	    GameRegistry.register(pertho);

	    algiz = (Algiz)(new Algiz().setUnlocalizedName("tw_rune_algiz"));
	    algiz.setRegistryName("rune_algiz_registry_name");
	    GameRegistry.register(algiz);

	    sowulo = (Sowulo)(new Sowulo().setUnlocalizedName("tw_rune_sowulo"));
	    sowulo.setRegistryName("rune_sowulo_registry_name");
	    GameRegistry.register(sowulo);

	    teiwaz = (Teiwaz)(new Teiwaz().setUnlocalizedName("tw_rune_teiwaz"));
	    teiwaz.setRegistryName("rune_teiwaz_registry_name");
	    GameRegistry.register(teiwaz);

	    berko = (Berko)(new Berko().setUnlocalizedName("tw_rune_berko"));
	    berko.setRegistryName("rune_berko_registry_name");
	    GameRegistry.register(berko);

	    ehwaz = (Ehwaz)(new Ehwaz().setUnlocalizedName("tw_rune_ehwaz"));
	    ehwaz.setRegistryName("rune_ehwaz_registry_name");
	    GameRegistry.register(ehwaz);

	    mann = (Mann)(new Mann().setUnlocalizedName("tw_rune_mann"));
	    mann.setRegistryName("rune_mann_registry_name");
	    GameRegistry.register(mann);

	    laguz = (Laguz)(new Laguz().setUnlocalizedName("tw_rune_laguz"));
	    laguz.setRegistryName("rune_laguz_registry_name");
	    GameRegistry.register(laguz);

	    ingwaz = (Ingwaz)(new Ingwaz().setUnlocalizedName("tw_rune_ingwaz"));
	    ingwaz.setRegistryName("rune_ingwaz_registry_name");
	    GameRegistry.register(ingwaz);

	    othalan = (Othalan)(new Othalan().setUnlocalizedName("tw_rune_othalan"));
	    othalan.setRegistryName("rune_othalan_registry_name");
	    GameRegistry.register(othalan);

	    dagaz = (Dagaz)(new Dagaz().setUnlocalizedName("tw_rune_dagaz"));
	    dagaz.setRegistryName("rune_dagaz_registry_name");
	    GameRegistry.register(dagaz);

	    blank = (Blank)(new Blank().setUnlocalizedName("tw_rune_blank"));
	    blank.setRegistryName("rune_blank_registry_name");
	    GameRegistry.register(blank);

  }

  public static void initCommon()
  {
  }

  public static void postInitCommon()
  {
  }
}
