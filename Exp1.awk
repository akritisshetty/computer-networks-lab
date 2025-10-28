# awk - pattern matching

BEGIN {
	Count = 0;
}

{
	if ($1 == "d")
		Count++;
}

END {
	printf("Number of packets = %d", Count);
}
