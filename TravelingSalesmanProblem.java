package TSP;

import java.util.Arrays;

public class TravelingSalesmanProblem {


    //berikut adalah jarak pada gambar si sales
    private static int[][] distanceMatrix = {
            {0, 10, 15, 20},
            {10, 0, 35, 25},
            {15, 35, 0, 30},
            {20, 25, 30, 0}
    };

    private static int numCities = 4;
    private static int allVisitedMask;

    private static int[][] dp;


    private static int tsp(int currentNode, int visitedMask) {
        if (visitedMask == allVisitedMask) {
            return distanceMatrix[currentNode][0]; // Jika semua kota telah dikunjungi, kembali ke kota awal
        }

        if (dp[currentNode][visitedMask] != 0) {
            return dp[currentNode][visitedMask]; // Mengembalikan hasil perhitungan yang sudah ada jika sudah dihitung sebelumnya
        }

        int minDistance = Integer.MAX_VALUE;

        for (int nextNode = 0; nextNode < numCities; nextNode++) {
            if (nextNode != currentNode && (visitedMask & (1 << nextNode)) == 0) {
                // Jika kota selanjutnya bukan kota saat ini dan belum dikunjungi
                int distance = distanceMatrix[currentNode][nextNode] + tsp(nextNode, visitedMask | (1 << nextNode));
                // Menghitung jarak dari kota saat ini ke kota selanjutnya dan menjalankan rekursi untuk kota selanjutnya
                minDistance = Math.min(minDistance, distance); // Memperbarui jarak terpendek
            }
        }

        dp[currentNode][visitedMask] = minDistance; // Menyimpan hasil perhitungan ke dalam tabel DP
        return minDistance;
    }

    public static void main(String[] args) {
        allVisitedMask = (1 << numCities) - 1; // Mask untuk menandai semua kota telah dikunjungi
        dp = new int[numCities][1 << numCities]; // Tabel DP untuk menyimpan hasil perhitungan

        int shortestPathDistance = tsp(0, 1); // Memanggil fungsi TSP dengan kota awal 0 (kota pertama) dan mask kunjungan awal 1 (kota pertama telah dikunjungi)
        System.out.println("Shortest path distance: " + shortestPathDistance);
    }

}
