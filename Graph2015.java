import java.util.ArrayDeque;

/**
 *  This class contains the method to set up the nodes and edge information for a conected graph, the method to find out the articulation point 
 *  and the biconected component in a conected graph, and also the method of sweeping all the valus changes in biconect method, and leave a clean
 *  graph. It prints the information how the biconect method works on the graph and give the information of articulation point, 
 *  depth first order number of the nodes, the setting of back link, and biconected components.
 * 
 * 
 * @author Huachao Li, Student ID: 1310232
 * AUCSC 310 Assignment3, 2015.Nov.29th
 *
 */
class Graph2015
{
   private int MAX_VERTS;  //the total number of the nodes
   private String nodeList[];  //list of nodes
   private int dfn[]; // depth first number for all node
   private int nodeC[];  // list of the coler of the node(unvisited is 0, visiting is 1, visited is 2
   private int ap[];     //mark the ap if exist
   private int backV[];   // list of the back value
   private int bvc; //the counter of back value;
   private int adjMat[][];      // adjacency matrix
   private int counter;          // depth first counter number of depth first search
   private int leafV[];    //list of value to define if the node is a leaf
   private int pNode[];    //the parent node list
   private int root;       //the root value
   private boolean rootV;  // the boolean value which define if the root set up or not
   private ArrayDeque<Integer>  thestack;  //the grey node stack
   private int crootAP;     //if crrotAP>1, root is an AP
   private int printC[];   //Check if AP has printed in the biconected component

   /**
    * This method set up all the memory size and default values of the class
    * @param size- the total number of nodes of the graph
    */
   public Graph2015(int size)
   {
	  MAX_VERTS = size;  //default set, NEVER sweep
	  nodeList = new String[MAX_VERTS]; //default set, NEVER sweep
	  dfn = new int[MAX_VERTS];
	  nodeC = new int[MAX_VERTS];
	  ap = new int[MAX_VERTS];
	  leafV = new int[MAX_VERTS];
	  backV = new int[MAX_VERTS];
	  pNode = new int[MAX_VERTS];
	  printC = new int[MAX_VERTS];
	  for (int k = 0; k < MAX_VERTS; k++)
	  {
		   dfn[k] = 0;
		   nodeC[k] = 0;
		   ap[k] = 0;
		   leafV[k] = 0;
		   backV[k] = -1;
		   pNode[k] = -1;
		   printC[k] = 0;
	  }
      adjMat = new int[MAX_VERTS][MAX_VERTS]; //default set, NEVER sweep
      counter = 0;
      bvc = 0;
      thestack = new ArrayDeque();
      rootV = false;  // 
      crootAP = 0;
   }
   
   /**
    * This method is to set the the label of the node
    * @param index - the index of the label to set
    * @param lab- the value of the label to set
    */
   public void setLabel(int index, String label)
   {
	   nodeList[index] = label;
   }
   
   /**
    * Because this is the conected graph, this method add both (start, end) & (end, start) edge in the graph
    * @param start- the start node of the edge
    * @param end- the end node of the edge
    */
   public void addEdge(int start, int end)
      {
      adjMat[start][end] = 1;
      adjMat[end][start] = 1;
      }

   /**
    * This method is to print the order number and node label of the visiting node
    * @param node- the node to print for
    */
   public void printNode(int node)
   {
      System.out.println("Depth First Index of "+nodeList[node]+" is "+ counter);
   }
// ------------------------------------------------------------
   /**
    * This method is to find all the articulation point and biconected component of the graph
    * @param i- the index of the node which is visiting
    */
   public void biConnect(int i)
   {
	   //set the root value if it is a root
	   if (!rootV)
	   {
		   root = i;
		   rootV = true;
	   }
	   
	   nodeC[i] = 1;  // mark the node as visiting
	   thestack.push(i);
	   // push the index of the node into the stack

	   dfn[i] = counter;
	   //set the ordered number 
	   counter++;
	   //order number++;
	   printNode(i);
	   
	   for (int j = 0; j < MAX_VERTS; j++)
	   {
		   //Check if i is an AP
		   if(bvc > 0)
		   {
			   if(i !=root)
			   {
			   apCheck(i);
			   }
		   }
		   
		   //Check if (i, j) exists 
		   if (adjMat[i][j] > 0)
		   {
			   if(nodeC[j]==0)
			   {
				   // i is not a leaf
				   leafV[i]++;
				   // found a unvisited node
				   thestack.push(i);
				   biConnect(j);
			   }
			   else
			   {
				   //root has no parent, only non-root node need to find a parent
				   if(i != root)
				   {
					   //found a parent
					   if (pNode[i] == -1)
					   {
						   //no parent node found yet, set the pNode
						   pNode[i] = j;
					   }
					   else
				       {
						   //parent node alreadty found, check the dfn number
						   if(dfn[j] < dfn[pNode[i]])
						   {
							   //reset the pNode if the dfn[j] is smaller
							   pNode[i] = j;
						   }
					   }
				   }	   
			   }
		   }
	   }//end loop
	   nodeC[i] = 2; //mark the node as visited
	   
	   //Check if it is an AP
	   if(bvc> 0)
	   {
		   apCheck(i);
	   }
	   // leaf found
	   if(leafV[i] ==0)
	   {
		   //no back value set yet, set back value
		   if(bvc == 0)
		   {
			   backV[bvc] = pNode[i];
			   bvc++;
			   printSetback(i);
		   }
		   else if (dfn[backV[bvc-1]] > dfn[pNode[i]])
		   {
			   // if the dfn of the pNode is larger than the dfn back value, reset back value
			   backV[bvc-1] = pNode[i];
			   printSetback(i);
		   }
		   else
		   {
			   // add new back value
			   backV[bvc] = pNode[i];
			   bvc++;
			   printSetback(i);
		   }
			   //leaf found
			   System.out.println("        Found a leaf: " +nodeList[i]);
	   }
	   else if (i != root)// if it is not a leaf and not the root
	   {
		   //set the new back value if back value is empty
		   if(bvc == 0)
		   {
			   backV[bvc] = pNode[i];
			   bvc++;
			   printSetback(i);
		   }
		   else if(dfn[backV[bvc-1]] > dfn[pNode[i]])
		   {
			   backV[bvc-1] = pNode[i];
			   printSetback(i);
	       }
	   }
	   
	   if (i == root && nodeC[i] ==2)
	   {
		   sweep();
		   System.out.println("Biconected component algorithm is done. Graph sweep already, graph is clean now"+
		                      "\n ======================================== \n");
	   }
   }// end biconect
   
   /**
    * This method is to check if the node is AP and print the biconected component
    * @param node- the node to check if it is an AP
    */
   private void apCheck(int node)
   {
	   //AP found, print biconected component
	   if(backV[bvc-1] == node)
	   {
		   backV[bvc-1] = -1;
		   //the back value at backV[bvc-1] is released.
		   bvc--;
		   //Articulation point found at first time, biconected component found
		   if(ap[node] == 0)
		   {
			   if (node==0)
			   {
				   //AP(root) not found before, mark i as ap
				   if(crootAP>1)
				   {
				   ap[node] = 1;
				   //print AP
				   printAP(node);
				   }
			   }
			   else
			   {
			   //AP not found before, mark i as ap
			   ap[node] = 1;
			   //print AP
			   printAP(node);
			   }
		   }
		   //print biconected component
		   printbi(node);
	   }
   }
   /**
    * This method is to print the biconected conponent
    * @param i- the end node of the biconected conponent, which is also an AP node
    */
   private void printbi(int i)
   {
	   System.out.print("\n BCBCBCBCBCBCBCBCBCBCBCBCBCBCBCBCBCBCBCBCBCBCBCBCBCBCBCBC"+
               "\n BC Found bi component: ");
	   int theNode = thestack.pop();
	   while (theNode != i)
	   {
		   if (printC[theNode] ==0)
		   {
			   if(theNode == root)
			   {
				   crootAP++;
			   }
			   //Print only once in biconected component
			   System.out.print(nodeList[theNode] +" ");
			   printC[theNode]++;
			   theNode = thestack.pop();
		   }
		   else
		   {
			   theNode = thestack.pop();
		   }
	   }
	   System.out.print(nodeList[theNode] +"  \n BCBCBCBCBCBCBCBCBCBCBCBCBCBCBCBCBCBCBCBCBCBCBCBCBCBCBCBC \n \n");
	   thestack.push(theNode);
	   psweep();
   }
   /**
    * This method is to print the AP node if found first time
    * @param node- the AP node to print
    */
   private void printAP(int node)
   {
	   System.out.println("\n APAPAPAPAPAPAPAPAPAPAPAPAPAPAPAPAPAPAPAPAPAPAPAP" +
		   		"\n AP Found articulation point: " + nodeList[node] +
		   		"\n APAPAPAPAPAPAPAPAPAPAPAPAPAPAPAPAPAPAPAPAPAPAPAP " +
		   		"\n"); 
   }
   /**
    * This method is to print the set back value information for node i
    * @param i- the node to print the set back value information
    */
   private void printSetback(int i) 
   {
	   System.out.println("        Set backlink of "+nodeList[i] +" to "+pNode[i]+" ("+nodeList[pNode[i]]+")");
   }
   
   /**
    * This method is to sweep the print record in printbi() method
    */
   private void psweep()
   {
	   for (int k = 0; k < MAX_VERTS; k++)
	   {
		   printC[k] = 0;
	   }
   }
   
   /**
    * This method is to sweep all the value changed in biconect method, and reset them
    */
   private void sweep()
   {
	   for (int k = 0; k < MAX_VERTS; k++)
	   {
		   dfn[k] = 0;
		   nodeC[k] = 0;
		   ap[k] = 0;
		   leafV[k] = 0;
		   backV[k] = -1;
		   pNode[k] = -1;
		   printC[k] = 0;
	   }
	   counter = 0;
	   bvc = 0;
	   rootV = false;
	   thestack = new ArrayDeque();
	   crootAP =0;
   }
}