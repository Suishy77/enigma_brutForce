#include <stdio.h>
#include <unistd.h>
#include <time.h>
#include <limits.h>

int main(void)
{
	size_t search = 0;
	clock_t start = clock();
	printf("Veuillez entree un nombre : ");
	scanf("%zd", &search);
	for (size_t i = 0; i <= ULLONG_MAX; i++)
	{
		if (i == search)
		{
			clock_t end = clock();
			printf("Votre nombre etait %zd\nEt cela a pris %f sec\n", i, (double)(end - start) / CLOCKS_PER_SEC);
			break;
		}
	}
	return (0);
}