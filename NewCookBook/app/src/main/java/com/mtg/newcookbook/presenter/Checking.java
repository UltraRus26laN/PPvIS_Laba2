package com.mtg.newcookbook.presenter;

public class Checking {
        Boolean isOnline() {
            val runtime = Runtime.getRuntime();
            try {
                val ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
                val exitValue = ipProcess.waitFor();
                return exitValue == 0;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return false;
        }
        String RecipeGroup = "Recipe";
        String IngredientGroup = "Ingredient";
}
