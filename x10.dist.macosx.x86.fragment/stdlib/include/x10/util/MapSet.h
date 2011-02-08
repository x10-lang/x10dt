#ifndef __X10_UTIL_MAPSET_H
#define __X10_UTIL_MAPSET_H

#include <x10rt.h>


#define X10_UTIL_ABSTRACTCOLLECTION_H_NODEPS
#include <x10/util/AbstractCollection.h>
#undef X10_UTIL_ABSTRACTCOLLECTION_H_NODEPS
#define X10_UTIL_SET_H_NODEPS
#include <x10/util/Set.h>
#undef X10_UTIL_SET_H_NODEPS
namespace x10 { namespace util { 
template<class FMGL(K), class FMGL(V)> class Map;
} } 
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(T)> class Iterator;
} } 
namespace x10 { namespace util { 

template<class FMGL(T)> class MapSet;
template <> class MapSet<void>;
template<class FMGL(T)> class MapSet : public x10::util::AbstractCollection<FMGL(T)>
  {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    x10aux::ref<x10::util::Map<FMGL(T), x10_boolean> > FMGL(map);
    
    void _constructor(x10aux::ref<x10::util::Map<FMGL(T), x10_boolean> > map);
    
    virtual x10_int size();
    virtual x10_boolean contains(FMGL(T) v);
    virtual x10_boolean add(FMGL(T) v);
    virtual x10_boolean remove(FMGL(T) v);
    virtual void clear();
    virtual x10aux::ref<x10::lang::Iterator<FMGL(T)> > iterator();
    virtual x10aux::ref<x10::util::MapSet<FMGL(T)> > x10__util__MapSet____x10__util__MapSet__this(
      );
    
    // Serialization
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};
template <> class MapSet<void> : public x10::util::AbstractCollection<void>
{
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    
};

} } 
#endif // X10_UTIL_MAPSET_H

namespace x10 { namespace util { 
template<class FMGL(T)>
class MapSet;
} } 

#ifndef X10_UTIL_MAPSET_H_NODEPS
#define X10_UTIL_MAPSET_H_NODEPS
#include <x10/util/AbstractCollection.h>
#include <x10/util/Set.h>
#include <x10/util/Map.h>
#include <x10/lang/Boolean.h>
#include <x10/lang/Int.h>
#include <x10/lang/Iterator.h>
#ifndef X10_UTIL_MAPSET_H_GENERICS
#define X10_UTIL_MAPSET_H_GENERICS
#endif // X10_UTIL_MAPSET_H_GENERICS
#ifndef X10_UTIL_MAPSET_H_IMPLEMENTATION
#define X10_UTIL_MAPSET_H_IMPLEMENTATION
#include <x10/util/MapSet.h>


template<class FMGL(T)> void x10::util::MapSet<FMGL(T)>::_instance_init() {
    _I_("Doing initialisation for class: x10::util::MapSet<FMGL(T)>");
    
}


//#line 15 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/MapSet.x10": x10.ast.X10FieldDecl_c

//#line 17 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/MapSet.x10": x10.ast.X10ConstructorDecl_c
template<class FMGL(T)> void x10::util::MapSet<FMGL(T)>::_constructor(x10aux::ref<x10::util::Map<FMGL(T), x10_boolean> > map)
{
    this->::x10::util::AbstractCollection<FMGL(T)>::_constructor();
    {
     
    }
    
    //#line 17 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/MapSet.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::MapSet<FMGL(T)> >)this)->
      FMGL(map) =
      map;
    
}


//#line 19 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/MapSet.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10_int x10::util::MapSet<FMGL(T)>::size(
  ) {
    
    //#line 19 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/MapSet.x10": x10.ast.X10Return_c
    return x10::util::Set<FMGL(T)>::size(x10aux::nullCheck(x10::util::Map<FMGL(T), x10_boolean>::keySet(x10aux::nullCheck(((x10aux::ref<x10::util::MapSet<FMGL(T)> >)this)->
                                                                                                                            FMGL(map)))));
    
}

//#line 20 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/MapSet.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10_boolean x10::util::MapSet<FMGL(T)>::contains(
  FMGL(T) v) {
    
    //#line 20 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/MapSet.x10": x10.ast.X10Return_c
    return x10::util::Map<FMGL(T), x10_boolean>::containsKey(x10aux::nullCheck(((x10aux::ref<x10::util::MapSet<FMGL(T)> >)this)->
                                                                                 FMGL(map)), 
             v);
    
}

//#line 22 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/MapSet.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10_boolean x10::util::MapSet<FMGL(T)>::add(
  FMGL(T) v) {
    
    //#line 22 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/MapSet.x10": x10.ast.X10Return_c
    return (x10aux::struct_equals(x10::util::Map<FMGL(T), x10_boolean>::put(x10aux::nullCheck(((x10aux::ref<x10::util::MapSet<FMGL(T)> >)this)->
                                                                                                FMGL(map)), 
                                    v,
                                    true),
                                  X10_NULL));
    
}

//#line 23 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/MapSet.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10_boolean x10::util::MapSet<FMGL(T)>::remove(
  FMGL(T) v) {
    
    //#line 23 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/MapSet.x10": x10.ast.X10Return_c
    return (!x10aux::struct_equals(x10::util::Map<FMGL(T), x10_boolean>::remove(x10aux::nullCheck(((x10aux::ref<x10::util::MapSet<FMGL(T)> >)this)->
                                                                                                    FMGL(map)), 
                                     v), X10_NULL));
    
}

//#line 24 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/MapSet.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> void x10::util::MapSet<FMGL(T)>::clear(
  ) {
    {
        
        //#line 24 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/MapSet.x10": polyglot.ast.Eval_c
        x10::util::Map<FMGL(T), x10_boolean>::clear(x10aux::nullCheck(((x10aux::ref<x10::util::MapSet<FMGL(T)> >)this)->
                                                                        FMGL(map)));
        
        //#line 24 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/MapSet.x10": x10.ast.X10Return_c
        return;
    }
}

//#line 25 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/MapSet.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::lang::Iterator<FMGL(T)> >
  x10::util::MapSet<FMGL(T)>::iterator(
  ) {
    
    //#line 25 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/MapSet.x10": x10.ast.X10Return_c
    return x10::util::Set<FMGL(T)>::iterator(x10aux::nullCheck(x10::util::Map<FMGL(T), x10_boolean>::keySet(x10aux::nullCheck(((x10aux::ref<x10::util::MapSet<FMGL(T)> >)this)->
                                                                                                                                FMGL(map)))));
    
}

//#line 14 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/MapSet.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::util::MapSet<FMGL(T)> >
  x10::util::MapSet<FMGL(T)>::x10__util__MapSet____x10__util__MapSet__this(
  ) {
    
    //#line 14 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/MapSet.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::util::MapSet<FMGL(T)> >)this);
    
}
template<class FMGL(T)> void x10::util::MapSet<FMGL(T)>::_serialize_body(x10aux::serialization_buffer& buf) {
    x10::util::AbstractCollection<FMGL(T)>::_serialize_body(buf);
    buf.write(this->FMGL(map));
    
}

template<class FMGL(T)> void x10::util::MapSet<FMGL(T)>::_deserialize_body(x10aux::deserialization_buffer& buf) {
    x10::util::AbstractCollection<FMGL(T)>::_deserialize_body(buf);
    FMGL(map) = buf.read<x10aux::ref<x10::util::Map<FMGL(T), x10_boolean> > >();
}

template<class FMGL(T)> x10aux::RuntimeType x10::util::MapSet<FMGL(T)>::rtt;
template<class FMGL(T)> void x10::util::MapSet<FMGL(T)>::_initRTT() {
    const x10aux::RuntimeType *canonical = x10aux::getRTT<x10::util::MapSet<void> >();
    if (rtt.initStageOne(canonical)) return;
    const x10aux::RuntimeType* parents[2] = { x10aux::getRTT<x10::util::AbstractCollection<FMGL(T)> >(), x10aux::getRTT<x10::util::Set<FMGL(T)> >()};
    const x10aux::RuntimeType* params[1] = { x10aux::getRTT<FMGL(T)>()};
    x10aux::RuntimeType::Variance variances[1] = { x10aux::RuntimeType::invariant};
    const char *baseName = "x10.util.MapSet";
    rtt.initStageTwo(baseName, x10aux::RuntimeType::class_kind, 2, parents, 1, params, variances);
}
#endif // X10_UTIL_MAPSET_H_IMPLEMENTATION
#endif // __X10_UTIL_MAPSET_H_NODEPS
