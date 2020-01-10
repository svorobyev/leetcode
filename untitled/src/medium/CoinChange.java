package medium;

import java.util.*;

public class CoinChange {

    public static void main(String[] args) {
        CoinChange coinChange = new CoinChange();
        int amount = 9;
        final int[] coins = {3, 5};
        final int i = coinChange.coinChange1(coins, amount);
        System.out.println(i + " coins for " + amount);

    }

    private int c(int[] coins, int amount) {
        Arrays.sort(coins);
        List<Map<Integer, Integer>> values = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int coin : coins) {
            map.put(coin, 0);
        }
        values.add(map);
        final int length = coins.length;
        int attempt = 0;
        int k = 1;
        while (amount > 0) {
            if (k == length && amount % coins[length - k] != 0) {
                int q = values.get(attempt).get(coins[length - k + 1]) * coins[length - k + 1];
                amount += q - coins[length - k + 1];
                HashMap<Integer, Integer> map1 = map;
                map1.put(coins[length - k + 1], map.get(coins[length - k + 1]) - (attempt + 1));
                attempt++;
                k -= attempt + 1;
                values.add(map1);
                continue;
            }
            if (amount < coins[length - k]) {
                k++;
                continue;
            }
            amount -= coins[length - k];
            values.get(attempt).put(coins[length - k], values.get(attempt).get(coins[length - k]) + 1);

        }
        System.out.println(values);
        return -1;
    }

    private int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        Map<Integer, Integer> values = new HashMap<>();

        Arrays.sort(coins);
        for (int coin : coins) {
            values.put(coin, 0);
        }
        int l = coins.length;
        for (int i = l - 1; i >= 0; i--) {
            int coinValue = coins[i];
            if (amount == 0) break;
            if (coinValue <= amount) {
                int q = amount / coinValue;
                values.put(coins[i], q);
                amount %= coinValue;
            }
        }
        System.out.println(values);
        if (amount != 0) {
            return -1;
        }
        return values.values().stream().mapToInt(Integer::intValue).sum();
    }

    private int minCoins(int coins[], int V) {
        // base case
        if (V == 0) return 0;

        // Initialize result
        int res = Integer.MAX_VALUE;
        int m = coins.length;
        // Try every coin that has smaller value than V
        for (int i = 0; i < m; i++) {
            if (coins[i] <= V) {
                int sub_res = minCoins(coins, V - coins[i]);

                // Check for INT_MAX to avoid overflow and see if
                // result can minimized
                if (sub_res != Integer.MAX_VALUE && sub_res + 1 < res)
                    res = sub_res + 1;
            }
        }
        return res;
    }

    private int coinChange1(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }

        int[] dp = new int[amount + 1];

        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i == coin) {
                    dp[i] = 1;
                } else if (i > coin) {
                    if (dp[i - coin] == Integer.MAX_VALUE) {
                        continue;
                    }
                    dp[i] = Math.min(dp[i - coin] + 1, dp[i]);
                }
            }
        }

        if (dp[amount] == Integer.MAX_VALUE) {
            return -1;
        }

        return dp[amount];
    }

}
